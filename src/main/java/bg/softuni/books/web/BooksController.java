package bg.softuni.books.web;

import bg.softuni.books.model.dto.BookDTO;
import bg.softuni.books.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get book by the given ID")
    @ApiResponses(
            value={
                    @ApiResponse(responseCode = "200",
                            description = "If the book was discovered.",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = BookDTO.class))}),
                    @ApiResponse(responseCode = "400",  description = "If the Id was incorrect."),
                    @ApiResponse(responseCode = "404", description = "If the book was not found.")
            }
    )
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

        if (updatedBookDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedBookDTO);
    }


}