package bg.softuni.books.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Book was not found.")
public class BookNotFoundException extends RuntimeException{
  private  Long id;


    public BookNotFoundException(Long id) {
        super("Book with ID " + id + " not found!");
        this.id = id;

    }

    public Long getId() {
        return id;
    }


}
