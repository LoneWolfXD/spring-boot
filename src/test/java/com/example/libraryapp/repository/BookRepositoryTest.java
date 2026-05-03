package com.example.libraryapp.repository;

import com.example.libraryapp.entity.Author;
import com.example.libraryapp.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testFindAllWithAuthors() {
        Author author = new Author("Author 1", "Bio");
        entityManager.persist(author);

        Book book = new Book("Book 1", 2020, author);
        entityManager.persist(book);
        entityManager.flush();

        List<Book> books = bookRepository.findAllWithAuthors();
        
        assertNotNull(books);
        assertFalse(books.isEmpty());
        assertEquals("Book 1", books.get(0).getTitle());
        assertNotNull(books.get(0).getAuthor());
        assertEquals("Author 1", books.get(0).getAuthor().getName());
    }
}
