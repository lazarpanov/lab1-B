package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Author;
import mk.finki.ukim.wp.lab.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookRepository {
    public final List<Book> books;

    public BookRepository() {
        this.books = new ArrayList<>();
        books.add(new Book("978-0142437344", "The Great Gatsby", "Fiction", 1925, new ArrayList<>()));
        books.add(new Book("978-0061120084", "To Kill a Mockingbird", "Fiction", 1960, new ArrayList<>()));
        books.add(new Book("978-0451524935", "1984", "Science Fiction", 1949, new ArrayList<>()));
        books.add(new Book("978-0316769488", "The Catcher in the Rye", "Fiction", 1951, new ArrayList<>()));
        books.add(new Book("978-1980497193", "Pride and Prejudice", "Romance", 1813, new ArrayList<>()));
    }

    public List<Book> findAll() {
        return books;
    }

    public Book findByIsbn(String isbn) {
        return books.stream().filter(book -> book.getIsbn().equals(isbn)).findFirst().orElse(null);
    }

    public Author addAuthorToBook(Author author, Book book) {
        book.getAuthors().add(author);
        return author;
    }
}
