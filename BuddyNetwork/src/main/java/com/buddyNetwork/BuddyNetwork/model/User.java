package com.buddyNetwork.BuddyNetwork.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.buddyNetwork.BuddyNetwork.dto.UserAuthLoginRequestDTO;
import com.buddyNetwork.BuddyNetwork.dto.UserAuthRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Creating User Entity which mapped to a table in the PostgreSQL database
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String fullname;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private char gender;

    @Column(nullable = false)
    private boolean isadmin;

    @CreationTimestamp
    @Column(updatable = false, name = "create_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    public User(UserAuthLoginRequestDTO logReq) {
        this.email = logReq.email();
        this.password = logReq.password();
    }

    public User() {
    }

    public User(UserAuthRequestDTO userAuthRequestDTO) {
        this.username = userAuthRequestDTO.username();
        this.fullname = userAuthRequestDTO.fullname();
        this.password = userAuthRequestDTO.password();
        this.email = userAuthRequestDTO.email();
        this.gender = userAuthRequestDTO.gender();
        this.isadmin = userAuthRequestDTO.isadmin();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean getIsadmin() {
        return isadmin;
    }

    public char getGender() {
        return gender;
    }

    public void setUsername(String _username) {
        this.username = _username;
    }

    public void setFullname(String _fullname) {
        this.fullname = _fullname;
    }

    public void setPassword(String _password) {
        this.password = _password;
    }

    public void setIsadmin(boolean _isadmin) {
        this.isadmin = _isadmin;
    }

    public void setGender(char _gender) {
        this.gender = _gender;
    }

    public void setEmail(String _email) {
        this.email = _email;
    }
}
