package com.standard.service;

import com.standard.entity.Author;
import com.standard.exception.ApplicationException;
import com.standard.dto.mapper.AuthorMapper;
import com.standard.repository.AuthorRepository;
import com.standard.service.impl.AuthorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    AuthorRepository authorRepository;

    @InjectMocks
    AuthorServiceImpl authorService;

    @Test
    void whenGetAll_shouldReturnList() {
        List<Author> mockAuthors = List.of(Author.builder().build());
        when(authorRepository.findAll()).thenReturn(mockAuthors);
//        doReturn(mockAuthors).when(authorRepository).findAll();

        List<Author> actualAuthors = authorService.getAll();
        assertEquals(mockAuthors, actualAuthors);
//        assertThat(actualAuthors.size()).isEqualTo(mockAuthors.size());

        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void givenExistedId_whenGetById_shouldReturnAuthor() {
        Author mockAuthor = Author.builder().id(1L).build();
        when(authorRepository.findById(1L)).thenReturn(Optional.of(mockAuthor));

        Author actualAuthor = authorService.getById(1L);
        assertEquals(mockAuthor.getId(), actualAuthor.getId());

        verify(authorRepository, times(1)).findById(1L);
    }

    @Test
    void givenNonExistId_whenGetById_shouldReturnException() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ApplicationException.class, () -> {
            authorService.getById(1L);
        });

        verify(authorRepository, times(1)).findById(1L);
    }

    @Test
    void givenAuthor_whenCreate_shouldReturnAuthorSuccess() {
        Author mockAuthor = Author.builder().build();
        when(authorRepository.save(mockAuthor)).thenReturn(mockAuthor);

        Author actualAuthor = authorService.create(AuthorMapper.INSTANCE.toRequest(mockAuthor));

        assertEquals(mockAuthor.getId(), actualAuthor.getId());

        verify(authorRepository, times(1)).save(mockAuthor);
    }

    @Test
    void givenAuthor_whenUpdate_shouldReturnAuthorSuccess() {
        Author mockAuthor = Author.builder().build();
        when(authorRepository.save(mockAuthor)).thenReturn(mockAuthor);

        Author actualAuthor = authorService.update(mockAuthor.getId(), AuthorMapper.INSTANCE.toRequest(mockAuthor));

        assertEquals(mockAuthor.getId(), actualAuthor.getId());

        verify(authorRepository, times(1)).save(mockAuthor);
    }

    @Test
    void givenExistedId_whenDelete_shouldSuccess() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(new Author()));

        authorService.delete(1L);

        verify(authorRepository, times(1)).delete(any(Author.class));
    }

    @Test
    void givenNonExistId_whenDelete_shouldReturnException() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ApplicationException.class, () -> {
            authorService.delete(1L);
        });

        verify(authorRepository, times(1)).findById(any());
        verify(authorRepository, never()).delete(any(Author.class));
    }

}
