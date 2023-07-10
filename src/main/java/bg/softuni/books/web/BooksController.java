package bg.softuni.books.web;

import bg.softuni.books.model.dto.BookDTO;
import bg.softuni.books.service.BookService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/books")
public class BooksController {

  private final BookService bookService;


  public BooksController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public ResponseEntity<List<BookDTO>> getAllBooks() {
    ResponseEntity<List<BookDTO>> ok = ResponseEntity.
            ok(bookService.getAllBooks());
    return ok;
  }

  @GetMapping("/{id}")
  public ResponseEntity<BookDTO> getBookById(@PathVariable("id") Long bookId) {
    Optional<BookDTO> bookOpt = bookService.getBookById(bookId);
    if (bookOpt.isEmpty()) {
      return ResponseEntity.
              notFound().
              build();
    } else {
      return ResponseEntity.
              ok(bookOpt.get());
    }
  }

}