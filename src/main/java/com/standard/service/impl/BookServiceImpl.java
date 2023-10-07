package com.standard.service.impl;

import com.standard.constant.ErrorEnum;
import com.standard.dto.mapper.BookMapper;
import com.standard.dto.request.BookRequest;
import com.standard.dto.request.BookSearchRequest;
import com.standard.dto.response.BookSearchResponse;
import com.standard.entity.Book;
import com.standard.exception.ApplicationException;
import com.standard.repository.BookRepository;
import com.standard.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book getById(long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.orElseThrow(() ->
                new ApplicationException(ErrorEnum.RESOURCE_NOT_FOUND, "Book", "id", String.valueOf(id)));
    }

    @Override
    public Book create(BookRequest bookRequest) {
        Book book = BookMapper.INSTANCE.toEntity(bookRequest);
        return bookRepository.save(book);
    }

    @Override
    public Book update(long id, BookRequest bookRequest) {
        Book book = BookMapper.INSTANCE.toEntity(bookRequest);
        book.setId(id);
        return bookRepository.save(book);
    }

    @Override
    public void delete(long id) {
        Book book = getById(id);
        bookRepository.delete(book);
    }

    @Override
    public BookSearchResponse search(BookSearchRequest bookSearchRequest) {
        return bookRepository.search(bookSearchRequest);
    }

}
