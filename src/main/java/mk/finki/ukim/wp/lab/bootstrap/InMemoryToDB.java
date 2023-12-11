//package mk.finki.ukim.wp.lab.bootstrap;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.transaction.Transactional;
//import mk.finki.ukim.wp.lab.model.Author;
//import mk.finki.ukim.wp.lab.model.Book;
//import mk.finki.ukim.wp.lab.model.BookStore;
//import mk.finki.ukim.wp.lab.repository.jpa.AuthorRepository;
//import mk.finki.ukim.wp.lab.repository.jpa.BookRepository;
//import mk.finki.ukim.wp.lab.repository.jpa.BookStoreRepository;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class InMemoryToDB {
//    private final AuthorRepository authorRepository;
//    private final BookRepository bookRepository;
//    private final BookStoreRepository bookStoreRepository;
//
//    public InMemoryToDB(AuthorRepository authorRepository, BookRepository bookRepository, BookStoreRepository bookStoreRepository) {
//        this.authorRepository = authorRepository;
//        this.bookRepository = bookRepository;
//        this.bookStoreRepository = bookStoreRepository;
//    }
//
//    @PostConstruct
//    @Transactional
//    public void init() {
//        List<Author> inMemoryAuthors =  DataHolder.authors;
//        List<Book> inMemoryBooks =  DataHolder.books;
//        List<BookStore> inMemoryBookStores =  DataHolder.bookStores;
//
////        inMemoryAuthors.forEach(inMemoryAuthor -> {
////            Author author = new Author();
////            author.setName(inMemoryAuthor.getName());
////            author.setSurname(inMemoryAuthor.getSurname());
////            author.setBiography(inMemoryAuthor.getBiography());
////            author.setDateOfBirth(inMemoryAuthor.getDateOfBirth());
////            authorRepository.save(author);
////        });
//
////        inMemoryBooks.forEach(inMemoryBook -> {
////            Book book = new Book(inMemoryBook.getIsbn(), inMemoryBook.getTitle(), inMemoryBook.getGenre(), inMemoryBook.getYear(), inMemoryBook.getAuthors(), inMemoryBook.getBookStore());
////            bookRepository.save(book);
////        });
//
////        inMemoryBookStores.forEach(inMemoryBookStore -> {
////            BookStore bookStore = new BookStore(inMemoryBookStore.getName(), inMemoryBookStore.getCity(), inMemoryBookStore.getAddress());
////            bookStoreRepository.save(bookStore);
////        });
//    }
//}
