package com.buddyNetwork.BuddyNetwork.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buddyNetwork.BuddyNetwork.model.User;

// Provides all the necessary CRUD methods like save(), findAll(), findById(), and deleteById()
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsById(Long id);
}
