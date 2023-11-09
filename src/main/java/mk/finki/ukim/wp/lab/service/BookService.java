package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Author;
import mk.finki.ukim.wp.lab.model.Book;

import java.util.List;

public interface BookService{
    List<Book> listBooks();
    Author addAuthorToBook(Long authorId, String isbn);
    Book findBookByIsbn(String isbn);
    void deleteAuthorsForBook(Book book);
}
