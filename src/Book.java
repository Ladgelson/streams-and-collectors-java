import java.util.List;

public class Book {
    private String title;
    private List<Author> authors;
    private BookType type;

    private Double value;

    public Book(String title, List<Author> authors, BookType type, Double value) {
        this.title = title;
        this.authors = authors;
        this.type = type;
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public BookType getType() {
        return type;
    }

    public void setType(BookType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", type=" + type +
                ", value=" + value +
                '}';
    }
}
