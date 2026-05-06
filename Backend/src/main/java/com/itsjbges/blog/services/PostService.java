package com.itsjbges.blog.services;

import java.util.List;
import java.util.UUID;

import com.itsjbges.blog.domain.entities.Post;
import com.itsjbges.blog.domain.entities.User;

public interface PostService {
    List<Post> getAllPosts(UUID categoryId, UUID tagId);
    List<Post> getDraftPosts(User user);
}
