package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Author;
import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.repository.AuthorRepository;
import mk.finki.ukim.wp.lab.repository.BookRepository;
import mk.finki.ukim.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Author addAuthorToBook(Long authorId, String isbn) {
        return bookRepository.addAuthorToBook(authorRepository.findById(authorId).orElse(null), bookRepository.findByIsbn(isbn));
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public void deleteAuthorsForBook(Book book) {
        bookRepository.deleteAllAuthors(book);
    }
}
