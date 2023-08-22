package com.standard.mapper;

import com.standard.dto.AuthorRequest;
import com.standard.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    Author toEntity(AuthorRequest authorRequest);

}
