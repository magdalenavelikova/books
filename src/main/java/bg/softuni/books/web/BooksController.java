package bg.softuni.books.web;

import bg.softuni.books.model.dto.BookDTO;
import bg.softuni.books.service.BookService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
  return ResponseEntity.
            ok(bookService.getAllBooks());

  }

  @GetMapping("/{id}")
  public ResponseEntity<BookDTO> getBookById(@PathVariable("id") Long bookId) {
    Optional<BookDTO> bookOpt = bookService.getBookById(bookId);
    return bookOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.
            notFound().
            build());
  }

  @PostMapping
  public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO newBook,
                                            UriComponentsBuilder uriComponentsBuilder) {
    long newBookID = bookService.createBook(newBook);

    return
            ResponseEntity.
                    created(uriComponentsBuilder.path("/api/books/{id}").
                            build(newBookID)).
                    build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BookDTO> deleteBookById(@PathVariable("id") Long bookId) {
    bookService.deleteBookById(bookId);

    return ResponseEntity.
            noContent().
            build();
  }

  @PostMapping("/{id}")
  public ResponseEntity<BookDTO> updateBook(@PathVariable("id") Long id, @RequestBody BookDTO bookDTO) {

    BookDTO updatedBookDTO = this.bookService.persistBook(id, bookDTO);

    if(updatedBookDTO == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(updatedBookDTO);
  }


}