package com.standard.dto.mapper;

import com.standard.dto.request.AuthorRequest;
import com.standard.dto.response.AuthorResponse;
import com.standard.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    Author toEntity(AuthorRequest authorRequest);

    AuthorRequest toRequest(Author author);

    AuthorResponse toResponse(Author author);

    List<AuthorResponse> toResponses(List<Author> authors);

}
