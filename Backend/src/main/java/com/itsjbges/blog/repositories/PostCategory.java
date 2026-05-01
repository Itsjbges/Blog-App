package com.itsjbges.blog.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itsjbges.blog.domain.entities.Post;

@Repository
public interface PostCategory extends JpaRepository<Post, UUID> {

}
