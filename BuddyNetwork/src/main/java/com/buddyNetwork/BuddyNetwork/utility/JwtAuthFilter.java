package com.buddyNetwork.BuddyNetwork.utility;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.buddyNetwork.BuddyNetwork.service.UserService;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtil jwtService;
    private final UserService userService;

    public JwtAuthFilter(JwtUtil jwtUtil, UserService uService) {
        this.jwtService = jwtUtil;
        this.userService = uService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filter)
            throws ServletException, IOException {
        String path = req.getServletPath();

        // Skip JWT check for public endpoints
        if (path.startsWith("/api/auth/v1/register") || path.startsWith("/api/auth/v1/login")
                || path.equals("/api/public") || path.equals("/main.js")
                || path.equals("/index.html") || path.equals("/") || path.equals("/ws/**") || path.equals("/hello")) {
            filter.doFilter(req, res);
            return;
        }

        String authHeader = req.getHeader("Authorization");

        try {

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                String userName = jwtService.extractUsername(token);
                log.info("AuthHeader: " + userName);

                if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails user = userService.loadUserByUsername(userName);

                    if (jwtService.validateToken(token, user)) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user,
                                null, user.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
                filter.doFilter(req, res);
            }
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (JwtException e) {
            throw new JwtException("Token error: " + e.getLocalizedMessage());
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}
