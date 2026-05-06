package com.itsjbges.blog.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itsjbges.blog.domain.dtos.TagResponse;
import com.itsjbges.blog.domain.entities.Tag;
import com.itsjbges.blog.mappers.TagMapper;
import com.itsjbges.blog.services.TagService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagResponse>> getAllTags() {
        List<Tag> tags = tagService.getTags();
        List<TagResponse> tagResponses = tags.stream().map(tag -> tagMapper.toTagResponse(tag)).toList();
        return ResponseEntity.ok(tagResponses);
    }
}
