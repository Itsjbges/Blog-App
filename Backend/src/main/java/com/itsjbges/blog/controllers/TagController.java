package com.itsjbges.blog.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itsjbges.blog.domain.dtos.CreateTagRequest;
import com.itsjbges.blog.domain.dtos.TagDto;
import com.itsjbges.blog.domain.entities.Tag;
import com.itsjbges.blog.mappers.TagMapper;
import com.itsjbges.blog.services.TagService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagDto>> getAllTags() {
        List<Tag> tags = tagService.getTags();
        List<TagDto> tagResponses = tags.stream().map(tag -> tagMapper.toTagResponse(tag)).toList();
        return ResponseEntity.ok(tagResponses);
    }

    @PostMapping
    public ResponseEntity<List<TagDto>> createTags(@RequestBody @Valid CreateTagRequest createTagRequest) {
        List<Tag> savedTags = tagService.createTags(createTagRequest.getNames());

        List<TagDto> createdTagResponses = savedTags.stream().map(tag -> tagMapper.toTagResponse(tag)).toList();

        return new ResponseEntity<>(
                createdTagResponses,
                HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable UUID id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }

}
