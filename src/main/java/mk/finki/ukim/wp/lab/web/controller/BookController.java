package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.model.BookStore;
import mk.finki.ukim.wp.lab.model.Review;
import mk.finki.ukim.wp.lab.service.BookService;
import mk.finki.ukim.wp.lab.service.BookStoreService;
import mk.finki.ukim.wp.lab.service.ReviewService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final BookStoreService bookStoreService;
    private final ReviewService reviewService;

    public BookController(BookService bookService, BookStoreService bookStoreService, ReviewService reviewService) {
        this.bookService = bookService;
        this.bookStoreService = bookStoreService;
        this.reviewService = reviewService;
    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error, Model model) {
//        bookStoreService.transferInMemoryToDataBase();
//        bookService.transferInMemoryToDataBase();
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Book> books = this.bookService.listBooks();
        model.addAttribute("books", books);

        return "listBooks";
    }

    @PostMapping
    public String postBooksPage(@RequestParam String book) {
        return "redirect:/author?isbn=" + book;
    }

    @GetMapping("/add")
    public String saveBook(@RequestParam String title,
                           @RequestParam String isbn,
                           @RequestParam String genre,
                           @RequestParam int year,
                           @RequestParam Long bookStore) {
        this.bookService.save(title, isbn, genre, year, bookStore);

        return "redirect:/books";
    }

    @PostMapping("/edit/{bookId}")
    public String editBook(@PathVariable Long bookId,
                           @RequestParam String title,
                           @RequestParam String isbn,
                           @RequestParam String genre,
                           @RequestParam Integer year,
                           @RequestParam Long bookStore) {
        if (bookId == -1) {
            return String.format("redirect:/books/add?title=%s&isbn=%s&genre=%s&year=%d&bookStore=%s", title, isbn, genre, year, bookStore);
        }
        bookService.deleteById(bookId);
//        Book book = bookService.findById(bookId);
//        Book book = new Book();
//        book.setTitle(title);
//        book.setIsbn(isbn);
//        book.setGenre(genre);
//        book.setYear(year);
//        book.setBookStore(bookStoreService.findById(bookStore));
        this.bookService.save(isbn, title, genre, year, bookStore);
        return "redirect:/books";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditBookForm(@PathVariable Long id, Model model) {
        if (id == -1) {
            List<BookStore> bookStores = this.bookStoreService.findAll();
            model.addAttribute("bookstores", bookStores);
            return "add-book";
        }
        if (this.bookService.findById(id) != null) {
            Book book = this.bookService.findById(id);
            List<BookStore> bookStores = this.bookStoreService.findAll();
            model.addAttribute("book", book);
            model.addAttribute("bookstores", bookStores);

            return "add-book";
        }

        return "redirect:/books?error=ProductNotFound";
    }

    @GetMapping("/add-form")
    public String getAddBookPage(Model model) {
        List<BookStore> bs = bookStoreService.findAll();
        model.addAttribute("bookstores", bs);

        return "add-book";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        this.bookService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/details")
    public String details(@RequestParam String isbn, @RequestParam(required = false) String authId, Model model) {
        Book book = bookService.findBookByIsbn(isbn);
        model.addAttribute("book", book);
        return "bookDetails";
    }

    @GetMapping("/review")
    public String review(Model model) {
//        List<Book> test = this.bookService.listBooks();
//        model.addAttribute("books", test);
//        return "review-for-book";
        List<Book> books = this.bookService.listBooks();
        model.addAttribute("books", books);

        return "review-for-book";
    }

    @PostMapping("/review")
    public String saveReview(@RequestParam Long id, @RequestParam String score, @RequestParam String description, @RequestParam("stamp") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime stamp) {
        Book book = bookService.findById(id);
        Review review = new Review(Integer.parseInt(score), description, book, stamp);
        reviewService.save(review);

        return "redirect:/books";
    }

    @GetMapping("/review/{id}")
    public String reviewsForBook(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);
        List<Review> reviews = this.reviewService.findAllReviewsByBook(book);
        model.addAttribute("book", book);
        model.addAttribute("reviews", reviews);
        return "review-list";
    }

    @PostMapping("/filter-reviews")
    public String filterReviewsForBook(@RequestParam Long id,
                                       @RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime from,
                                       @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime to, Model model) {
        Book book = bookService.findById(id);
        List<Review> reviews = this.reviewService.findReviewByTimestampBetweenAndBook(from, to, book);
        model.addAttribute("book", book);
        model.addAttribute("reviews", reviews);
        return "review-list";
    }
}
