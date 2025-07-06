package com.example.library.bookController;

import java.util.List;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.bookEntity.Book;
import com.example.library.bookService.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping

    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedbook = bookService.createBook(book);
        return ResponseEntity.ok(savedbook);
    }

    @PutMapping("/{id}")

    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        return ResponseEntity.ok(updatedBook);
    }

    @GetMapping

    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")

    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book bookById = bookService.getBookById(id);
        return ResponseEntity.ok(bookById);
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/titre/{titre}")

    public ResponseEntity<List<Book>> getByTitre(@PathVariable String titre) {
        List<Book> bookbytitre = bookService.getByTitre(titre);
        return ResponseEntity.ok(bookbytitre);
    }

    @GetMapping("/isbn/{isbn}")

    public ResponseEntity<List<Book>> getByIsbn(@PathVariable String isbn) {
        List<Book> bookbyISBN = bookService.getByTitre(isbn);
        return ResponseEntity.ok(bookbyISBN);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Book>> getByCategory(@PathVariable String Category) {
        List<Book> category = bookService.findByCategory(Category);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/sorted/{field}")
    public ResponseEntity<List<Book>> getBooksSortedBy(@PathVariable String field) {
        List<Book> sorted = bookService.getAllBooksSortedBy(field);
        return ResponseEntity.ok(sorted);
    }

    @GetMapping("/auteur/{auteur}")

    public ResponseEntity<List<Book>> getByAuteur(@PathVariable String auteur) {
        List<Book> bookbyAuteur = bookService.getByTitre(auteur);
        return ResponseEntity.ok(bookbyAuteur);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Book>> searchBooks(@PathVariable String keyword) {
        List<Book> resulta = bookService.searchBooks(keyword);
        return ResponseEntity.ok(resulta);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<Book>> getBookByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "titre") String sortBy) {

        Page<Book> bookbyPage = bookService.getBookByPage(page, size, sortBy);
        return ResponseEntity.ok(bookbyPage);

    }

}
