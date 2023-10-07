package com.standard.dto.mapper;

import com.standard.dto.request.BookRequest;
import com.standard.dto.response.BookResponse;
import com.standard.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book toEntity(BookRequest bookRequest);

    BookResponse toResponse(Book book);

    List<BookResponse> toResponses(List<Book> books);

}
