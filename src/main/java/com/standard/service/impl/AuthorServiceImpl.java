package com.standard.service.impl;

import com.standard.constant.ErrorEnum;
import com.standard.dto.request.AuthorRequest;
import com.standard.dto.response.AuthorResponse;
import com.standard.entity.Author;
import com.standard.exception.ApplicationException;
import com.standard.dto.mapper.AuthorMapper;
import com.standard.repository.AuthorRepository;
import com.standard.service.AuthorService;
import com.standard.util.ExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author getById(Long id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        return optionalAuthor.orElseThrow(() ->
                        new ApplicationException(ErrorEnum.RESOURCE_NOT_FOUND, "Author", "id", id));
    }

    @Override
    public Author create(AuthorRequest authorRequest) {
        Author author = AuthorMapper.INSTANCE.toEntity(authorRequest);
        return authorRepository.save(author);
    }

    @Override
    public Author update(long id, AuthorRequest authorRequest) {
        Author author = AuthorMapper.INSTANCE.toEntity(authorRequest);
        author.setId(id);
        return authorRepository.save(author);
    }

    @Override
    public void delete(Long id) {
        Author author = getById(id);
        authorRepository.delete(author);
    }

    @Override
    public List<AuthorResponse> getAllCustom() {
        return authorRepository.findAllCustom();
    }

    @Override
    public void exportExcel(HttpServletResponse response) throws IOException {
        String[] headersList = {"ID", "NAME", "DATE OF BIRTH", "DESCRIPTION"};
        List<Object[]> listDataAuthor = new ArrayList<>();
//        List<Author> authors = getAll();
//        for (Author author : authors) {
//            Object[] data = new Author[]{author};
//            System.out.println(Arrays.toString(data));
//        }
//        ExcelUtil.export(response, headersList, authors);
    }

}
