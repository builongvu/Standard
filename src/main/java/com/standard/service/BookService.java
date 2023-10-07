package com.standard.service;

import com.standard.dto.request.BookRequest;
import com.standard.dto.request.BookSearchRequest;
import com.standard.dto.response.BookSearchResponse;
import com.standard.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    Book getById(long id);

    Book create(BookRequest bookRequest);

    Book update(long id, BookRequest bookRequest);

    void delete(long id);

    BookSearchResponse search(BookSearchRequest bookSearchRequest);

}
