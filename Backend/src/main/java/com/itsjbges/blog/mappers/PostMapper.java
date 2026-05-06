package com.itsjbges.blog.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.itsjbges.blog.domain.CreatePostRequest;
import com.itsjbges.blog.domain.dtos.CreatePostRequestsDto;
import com.itsjbges.blog.domain.dtos.PostDto;
import com.itsjbges.blog.domain.entities.Post;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target = "author", source = "author")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "tags", source = "tags")
    PostDto toDto(Post post);

    CreatePostRequest toCreatePostRequest(CreatePostRequestsDto dto);
}
