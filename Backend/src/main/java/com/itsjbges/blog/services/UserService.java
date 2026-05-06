package com.itsjbges.blog.services;

import java.util.UUID;

import com.itsjbges.blog.domain.entities.User;

public interface UserService {
    User getUserById(UUID id);
}
