package com.example.libraryapp.service;

import com.example.libraryapp.entity.Author;
import com.example.libraryapp.entity.Book;
import com.example.libraryapp.repository.AuthorRepository;
import com.example.libraryapp.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LibraryServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private LibraryService libraryService;

    private Author author;
    private Book book;

    @BeforeEach
    void setUp() {
        author = new Author("Test Author", "Bio");
        author.setId(1L);

        book = new Book("Test Book", 2024, author);
        book.setId(1L);
    }

    @Test
    void testGetAllBooksWithAuthors() {
        when(bookRepository.findAllWithAuthors()).thenReturn(Arrays.asList(book));

        List<Book> books = libraryService.getAllBooksWithAuthors();

        assertNotNull(books);
        assertEquals(1, books.size());
        assertEquals("Test Book", books.get(0).getTitle());
        verify(bookRepository, times(1)).findAllWithAuthors();
    }

    @Test
    void testSaveBookWithNewAuthor() {
        Author newAuthor = new Author("New Author", "");
        Book newBook = new Book("New Book", 2024, newAuthor);
        
        when(authorRepository.save(newAuthor)).thenReturn(newAuthor);
        when(bookRepository.save(newBook)).thenReturn(newBook);

        Book savedBook = libraryService.saveBook(newBook);

        assertNotNull(savedBook);
        verify(authorRepository, times(1)).save(newAuthor);
        verify(bookRepository, times(1)).save(newBook);
    }
}
