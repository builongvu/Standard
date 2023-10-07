package com.standard.controller;

import com.standard.dto.mapper.AuthorMapper;
import com.standard.dto.request.AuthorSearchRequest;
import com.standard.dto.response.ApiResponse;
import com.standard.dto.request.AuthorRequest;
import com.standard.dto.response.AuthorResponse;
import com.standard.dto.response.AuthorSearchResponse;
import com.standard.service.AuthorService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        List<AuthorResponse> authorResponses = AuthorMapper.INSTANCE.toResponses(authorService.getAll());
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(authorResponses)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable Long id) {
        AuthorResponse authorResponse = AuthorMapper.INSTANCE.toResponse(authorService.getById(id));
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(authorResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody @Valid AuthorRequest authorRequest) {
        AuthorResponse savedAuthorResponse = AuthorMapper.INSTANCE.toResponse(authorService.create(authorRequest));
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.CREATED.value())
                .data(savedAuthorResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable long id, @RequestBody AuthorRequest authorRequest) {
        AuthorResponse updatedAuthorResponse = AuthorMapper.INSTANCE.toResponse(authorService.update(id, authorRequest));
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(updatedAuthorResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        authorService.delete(id);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/search")
    public ResponseEntity<ApiResponse> search(@RequestBody AuthorSearchRequest authorSearchRequest) {
        AuthorSearchResponse authorSearchResponse = authorService.search(authorSearchRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK.value())
                .data(authorSearchResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/excel/export")
    public void export(HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=author.xlsx");
        authorService.exportExcel(response);
    }

}
