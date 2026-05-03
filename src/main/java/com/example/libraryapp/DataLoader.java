package com.example.libraryapp;

import com.example.libraryapp.entity.Author;
import com.example.libraryapp.entity.Book;
import com.example.libraryapp.service.LibraryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    private final LibraryService libraryService;

    public DataLoader(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Override
    public void run(String... args) throws Exception {
        Author a1 = new Author("J.R.R. Tolkien", "English writer and philologist");
        Author a2 = new Author("George Orwell", "English novelist and essayist");
        Author a3 = new Author("J.K. Rowling", "British author");
        Author a4 = new Author("Agatha Christie", "English writer known for detective novels");
        Author a5 = new Author("Stephen King", "American author of horror");

        libraryService.saveAuthor(a1);
        libraryService.saveAuthor(a2);
        libraryService.saveAuthor(a3);
        libraryService.saveAuthor(a4);
        libraryService.saveAuthor(a5);

        libraryService.saveBook(new Book("The Hobbit", 1937, a1));
        libraryService.saveBook(new Book("The Fellowship of the Ring", 1954, a1));
        libraryService.saveBook(new Book("1984", 1949, a2));
        libraryService.saveBook(new Book("Animal Farm", 1945, a2));
        libraryService.saveBook(new Book("Harry Potter and the Philosopher's Stone", 1997, a3));
        libraryService.saveBook(new Book("Harry Potter and the Chamber of Secrets", 1998, a3));
        libraryService.saveBook(new Book("Murder on the Orient Express", 1934, a4));
        libraryService.saveBook(new Book("And Then There Were None", 1939, a4));
        libraryService.saveBook(new Book("The Shining", 1977, a5));
        libraryService.saveBook(new Book("It", 1986, a5));
        
        System.out.println("Database initialized with 10 books and 5 authors.");
    }
}
