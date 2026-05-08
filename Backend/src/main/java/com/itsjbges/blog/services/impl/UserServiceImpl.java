package com.itsjbges.blog.services.impl;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itsjbges.blog.domain.entities.User;
import com.itsjbges.blog.repositories.UserRepository;
import com.itsjbges.blog.services.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUserById(UUID id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    @Override
    @Transactional
    public User createUser(String name, String password, String email) {

        if (userRepository.existsByEmailIgnoreCase(email)) {
            throw new IllegalArgumentException("There is already a user with the email: " + email);
        }

        User createdUser = User.builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();

        return userRepository.save(createdUser);
    }
}
