package bg.softuni.books.model.dto;

public class BookErrorDTO {
    private final long bookId;
    private final String description;

    public BookErrorDTO(long bookId, String description) {
        this.bookId = bookId;
        this.description = description;
    }

    public long getBookId() {
        return bookId;
    }

    public String getDescription() {
        return description;
    }
}
