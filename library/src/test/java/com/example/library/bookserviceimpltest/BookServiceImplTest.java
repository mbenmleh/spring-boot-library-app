package com.example.library.bookserviceimpltest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.library.bookEntity.Book;
import com.example.library.bookRepository.BookRepository;
import com.example.library.bookService.BookService;
import com.example.library.bookService.impl.BookServiceImpl;

public class BookServiceImplTest {
    private BookRepository bookRepository;
    private BookService bookService;

    @BeforeEach
    void setupd() {
        bookRepository = mock(BookRepository.class);
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test

    void testGetAllBooks() {
        Book book1 = new Book("Test Book", "789", "Author", LocalDateTime.now());
        Book book2 = new Book("Test Book2", "777", "Author2", LocalDateTime.now());

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<Book> result = bookService.getAllBooks();
        assertEquals(2, result.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testCreateBook() {
        Book book = new Book("Test Create", "1000", "Mohamed", LocalDateTime.now());
        when(bookRepository.save(ArgumentMatchers.any(Book.class))).thenReturn(book);
        Book saved = bookService.createBook(book);

        assertEquals("Test Create", saved.getTitre());
        verify(bookRepository).save(book);

    }

    @Test
    void testUpdate() {
        Book existing = new Book("existbook", "100", "Achref", LocalDateTime.now());
        Book update = new Book("newbook", "1000", "imed", LocalDateTime.now());

        when(bookRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(bookRepository.save(any(Book.class))).thenReturn(update);

        Book result = bookService.updateBook(1L, update);
        assertEquals("newbook", result.getTitre());
        assertEquals("1000", result.getIsbn());
        verify(bookRepository).save(existing);
    }

    @Test
    void testBookbyID() {
        Book book = new Book("findbook", "100", "Achref", LocalDateTime.now());
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        Book result = bookService.getBookById(1L);
        assertNotNull(result);
        assertEquals("findbook", result.getTitre());
        verify(bookRepository).findById(1L);
    }

    @Test
    void testDeleteByid() {
        doNothing().when(bookRepository).deleteById(1L);
        bookService.deleteBook(1L);
        verify(bookRepository).deleteById(1L);
    }

}
