package com.itsjbges.blog.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.itsjbges.blog.domain.PostStatus;
import com.itsjbges.blog.domain.dtos.CategoryDto;
import com.itsjbges.blog.domain.dtos.CreateCategoryRequest;
import com.itsjbges.blog.domain.entities.Category;
import com.itsjbges.blog.domain.entities.Post;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    // Nge map hasil ke "postCount" yg gk ada dari db ke DTO kt dengan liet posts
    // list di category then pake fungsi di qualifiedByName untuk masukin
    // valuenya ke postCount
    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    CategoryDto toDto(Category category);

    // MapStruct liet variables di source and the target then map it
    // Mknya perlu ada ReportingPolicy.IGNORE supaya variable yg gmw ada di DTO
    // Bisa di ignore and code kt gk throw error
    Category toEntity(CreateCategoryRequest createCategoryRequest);

    // Perlu pake annotation inti supaya @Mapping bisa ketemu the function
    @Named("calculatePostCount")
    default long calculatePostCount(List<Post> posts) {
        if (null == posts) {
            return 0;
        }

        return posts.stream()
                .filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
                .count();
    }

}
