package com.example.library.bookController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.bookEntity.Book;
import com.example.library.bookService.BookService;



@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

@PostMapping

public ResponseEntity<Book> createBook(@RequestBody Book book){
    Book savedbook = bookService.createBook(book);
    return ResponseEntity.ok(savedbook);
}

@PutMapping("/{id}")

public ResponseEntity<Book> updateBook(@PathVariable Long id,@RequestBody Book book){
    Book updatedBook = bookService.updateBook(id, book);
    return ResponseEntity.ok(updatedBook);
}
@GetMapping
    
public ResponseEntity<List<Book>> getAllBooks(){
    return ResponseEntity.ok(bookService.getAllBooks());
}
@GetMapping("/{id}")
    
public ResponseEntity<Book> getBookById(@PathVariable Long id){
    Book bookById = bookService.getBookById(id);
    return ResponseEntity.ok(bookById);
}

@DeleteMapping("/{id}")

public ResponseEntity<Void> deleteBook(@PathVariable Long id){
    bookService.deleteBook(id);
    return ResponseEntity.noContent().build();
}

}
