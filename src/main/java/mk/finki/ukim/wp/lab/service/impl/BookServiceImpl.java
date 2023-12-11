package mk.finki.ukim.wp.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.finki.ukim.wp.lab.bootstrap.DataHolder;
import mk.finki.ukim.wp.lab.model.Author;
import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.model.BookStore;
import mk.finki.ukim.wp.lab.repository.jpa.AuthorRepository;
import mk.finki.ukim.wp.lab.repository.jpa.BookRepository;
import mk.finki.ukim.wp.lab.repository.jpa.BookStoreRepository;
import mk.finki.ukim.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookStoreRepository bookStoreRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, BookStoreRepository bookStoreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Author addAuthorToBook(Long authorId, String isbn) {
//        return bookRepository.addAuthorToBook(authorRepository.findById(authorId).orElse(null), bookRepository.findByIsbn(isbn));
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new RuntimeException("author not found"));
        Book book = bookRepository.findByIsbn(isbn).orElseThrow(() -> new RuntimeException("book not found"));
        book.getAuthors().add(author);
        this.bookRepository.save(book);
        return author;
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return this.bookRepository.findByIsbn(isbn).orElseThrow(() -> new RuntimeException("book not found"));
    }

    @Override
    public void deleteAuthorsForBook(Book book) {
        book.setAuthors(new ArrayList<>());
    }

    @Override
    public Optional<Book> save(String title, String isbn, String genre, int year, Long bookStoreId) {
        BookStore bookStore = bookStoreRepository.findById(bookStoreId).orElseThrow(() -> new RuntimeException("book store not found"));

        Book book = new Book(title, isbn, genre, year, new ArrayList<>(), bookStore);
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("book not found"));
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void transferInMemoryToDataBase() {
//        List<Book> inMemoryBooks = DataHolder.books;
//        inMemoryBooks.forEach(inMemoryBook -> {
//            BookStore bs = bookStoreRepository.findBookStoreByName(inMemoryBook.getBookStore().getName());
//            Book book = new Book(inMemoryBook.getIsbn(), inMemoryBook.getTitle(), inMemoryBook.getGenre(), inMemoryBook.getYear(), new ArrayList<>(), bs);
//            bookRepository.save(book);
//        });
    }
}
