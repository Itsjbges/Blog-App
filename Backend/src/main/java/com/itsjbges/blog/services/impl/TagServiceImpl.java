package com.itsjbges.blog.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itsjbges.blog.domain.entities.Tag;
import com.itsjbges.blog.repositories.TagRepository;
import com.itsjbges.blog.services.TagService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService{

    private final TagRepository tagRepository;

    @Override
    public List<Tag> getTags() {
        return tagRepository.findAllWithPostCount();
    }
    
}
