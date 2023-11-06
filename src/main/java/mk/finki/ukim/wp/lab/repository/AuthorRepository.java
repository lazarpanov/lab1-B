package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository {
    private final List<Author> authors;

    public AuthorRepository() {
        this.authors = new ArrayList<>();
        this.authors.add(new Author(1L, "John", "Doe", "John Doe is a talented writer with a passion for storytelling."));
        this.authors.add(new Author(2L, "Jane", "Smith", "Jane Smith is an accomplished author known for her best-selling novels."));
        this.authors.add(new Author(3L, "Robert", "Johnson", "Robert Johnson's writing style captivates readers with its unique blend of humor and drama."));
        this.authors.add(new Author(4L, "Maria", "Garcia", "Maria Garcia is a renowned author whose novels have been translated into multiple languages."));
        this.authors.add(new Author(5L, "Michael", "Brown", "Michael Brown is an emerging writer with a fresh perspective on contemporary issues."));
    }
    public List<Author> findAll() {
        return authors;
    }

    public Optional<Author> findById(Long id)
    {
        return authors.stream().filter(author -> author.getId().equals(id)).findFirst();
    }
}
