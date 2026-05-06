package com.itsjbges.blog.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itsjbges.blog.domain.CreatePostRequest;
import com.itsjbges.blog.domain.UpdatePostRequest;
import com.itsjbges.blog.domain.dtos.CreatePostRequestsDto;
import com.itsjbges.blog.domain.dtos.PostDto;
import com.itsjbges.blog.domain.dtos.UpdatePostRequestDto;
import com.itsjbges.blog.domain.entities.Post;
import com.itsjbges.blog.domain.entities.User;
import com.itsjbges.blog.mappers.PostMapper;
import com.itsjbges.blog.services.PostService;
import com.itsjbges.blog.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(required = false) UUID categoryId,
            @RequestParam(required = false) UUID tagId) {

        List<Post> posts = postService.getAllPosts(categoryId, tagId);
        List<PostDto> postDtos = posts.stream().map(post -> postMapper.toDto(post)).toList();
        return ResponseEntity.ok(postDtos);
    }

    @GetMapping(path = "/drafts")
    public ResponseEntity<List<PostDto>> getDrafts(@RequestAttribute UUID userId) {
        User loggedInUser = userService.getUserById(userId);
        List<Post> draftPosts = postService.getDraftPosts(loggedInUser);
        List<PostDto> postDtos = draftPosts.stream().map(postMapper::toDto).toList();
        return ResponseEntity.ok(postDtos);
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(
            @Valid @RequestBody CreatePostRequestsDto createPostRequestsDto,
            @RequestAttribute UUID userId) {

        User loggedInUser = userService.getUserById(userId);
        CreatePostRequest createCategoryRequest = postMapper.toCreatePostRequest(createPostRequestsDto);

        Post createdPost = postService.createPost(loggedInUser, createCategoryRequest);

        PostDto createdPostDto = postMapper.toDto(createdPost);

        return new ResponseEntity<>(
                createdPostDto,
                HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PostDto> updatePost(
            @PathVariable UUID id,
            @Valid @RequestBody UpdatePostRequestDto updatePostRequestDto) {

        UpdatePostRequest updatePostRequest = postMapper.toUpdatePostRequest(updatePostRequestDto);

        Post updatedPost = postService.updatePost(id, updatePostRequest);

        PostDto updatedPostDto = postMapper.toDto(updatedPost);

        return ResponseEntity.ok(updatedPostDto);

    }

}
