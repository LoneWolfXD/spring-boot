package com.example.libraryapp.service;

import com.example.libraryapp.entity.Author;
import com.example.libraryapp.entity.Book;
import com.example.libraryapp.repository.AuthorRepository;
import com.example.libraryapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public LibraryService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooksWithAuthors() {
        return bookRepository.findAllWithAuthors();
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public Book saveBook(Book book) {
        if (book.getAuthor() != null && book.getAuthor().getId() == null) {
            authorRepository.save(book.getAuthor());
        }
        return bookRepository.save(book);
    }

    @Transactional
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
