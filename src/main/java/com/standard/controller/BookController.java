package com.standard.controller;

import com.standard.dto.mapper.BookMapper;
import com.standard.dto.request.BookRequest;
import com.standard.dto.request.BookSearchRequest;
import com.standard.dto.response.ApiResponse;
import com.standard.dto.response.BookResponse;
import com.standard.dto.response.BookSearchResponse;
import com.standard.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        List<BookResponse> bookResponses = BookMapper.INSTANCE.toResponses(bookService.getAll());
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(bookResponses)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable long id) {
        BookResponse bookResponse = BookMapper.INSTANCE.toResponse(bookService.getById(id));
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(bookResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid BookRequest bookRequest) {
        BookResponse bookResponse = BookMapper.INSTANCE.toResponse(bookService.create(bookRequest));
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.CREATED.value())
                .data(bookResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable long id, @RequestBody @Valid BookRequest bookRequest) {
        BookResponse bookResponse = BookMapper.INSTANCE.toResponse(bookService.update(id, bookRequest));
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(bookResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable long id) {
        bookService.delete(id);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(HttpStatus.OK.name())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/search")
    public ResponseEntity<ApiResponse> search(@RequestBody BookSearchRequest bookSearchRequest) {
        BookSearchResponse bookSearchResponse = bookService.search(bookSearchRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(bookSearchResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
