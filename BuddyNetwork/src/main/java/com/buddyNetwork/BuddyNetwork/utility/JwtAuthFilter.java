package com.buddyNetwork.BuddyNetwork.utility;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.buddyNetwork.BuddyNetwork.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtil jwtService;
    private final UserService userService;

    public JwtAuthFilter(JwtUtil jwtUtil, UserService uService) {
        this.jwtService = jwtUtil;
        this.userService = uService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filter) {
        try {
            String authHeader = req.getHeader("Authorization");

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                String userName = jwtService.extractUsername(token);

                if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails user = userService.loadUserByUsername(userName);

                    if (jwtService.validateToken(token, user)) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user,
                                null, user.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            }
            filter.doFilter(req, res);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
