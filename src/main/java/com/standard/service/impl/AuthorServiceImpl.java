package com.standard.service.impl;

import com.standard.constant.ErrorEnum;
import com.standard.dto.AuthorRequest;
import com.standard.entity.Author;
import com.standard.exception.ApplicationException;
import com.standard.mapper.AuthorMapper;
import com.standard.repository.AuthorRepository;
import com.standard.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author getById(Integer id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        return optionalAuthor.orElseThrow(() -> new ApplicationException(ErrorEnum.RESOURCE_NOT_FOUND, "id", id.toString()));
    }

    @Override
    public Author create(AuthorRequest authorRequest) {
        Author author = AuthorMapper.INSTANCE.toEntity(authorRequest);
        return authorRepository.saveAndFlush(author);
    }

    @Override
    public Author update(AuthorRequest authorRequest) {
        return create(authorRequest);
    }

    @Override
    public void delete(Integer id) {
        authorRepository.delete(authorRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorEnum.RESOURCE_NOT_FOUND, "id", id.toString())));
    }

}
