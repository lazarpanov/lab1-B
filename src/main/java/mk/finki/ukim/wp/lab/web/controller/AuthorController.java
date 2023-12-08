package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.model.Author;
import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.model.BookStore;
import mk.finki.ukim.wp.lab.service.AuthorService;
import mk.finki.ukim.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;

    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping
    public String authorGet(@RequestParam(value = "isbn", required = false) String isbn, Model model) {
        model.addAttribute("authors", authorService.listAuthors());
        model.addAttribute("isbn", isbn);

        return "authorsList";
    }

    @PostMapping
    public String authorPost(@RequestParam("authId") String authId, @RequestParam("isbn") String isbn) {
        bookService.addAuthorToBook(Long.parseLong(authId), isbn);
        System.out.println(authId);
        System.out.println(isbn);
        return "redirect:/books/details?isbn=" + isbn + "&authId=" + authId;
    }

    @GetMapping("/delete")
    public String deleteAuthors(@RequestParam String isbn) {
        bookService.deleteAuthorsForBook(bookService.findBookByIsbn(isbn));
        return "redirect:/books/details?isbn=" + isbn;
    }

    @GetMapping("/deleteone/{id}")
    public String deleteOneAuthor(@PathVariable String id) {
        authorService.deleteById(Long.parseLong(id));
        return "redirect:/author";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditAuthor(@PathVariable Long id, Model model) {
        if (this.authorService.findById(id) != null) {
            Author author = this.authorService.findById(id);
            model.addAttribute("author", author);

            return "add-author";
        }
        return "redirect:/author?error=ProductNotFound";
    }

    @PostMapping("/edit/{id}")
    public String editAuthor(@PathVariable Long id,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String biography) {
        Author author = this.authorService.findById(id);
        author.setName(name);
        author.setSurname(surname);
        author.setBiography(biography);

        return "redirect:/author";
    }
}
