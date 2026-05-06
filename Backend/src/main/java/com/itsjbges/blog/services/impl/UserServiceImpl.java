package com.itsjbges.blog.services.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.itsjbges.blog.domain.entities.User;
import com.itsjbges.blog.repositories.UserRepository;
import com.itsjbges.blog.services.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    
    private final UserRepository userRepository;
    
    @Override
    public User getUserById(UUID id) {
        return userRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

}
