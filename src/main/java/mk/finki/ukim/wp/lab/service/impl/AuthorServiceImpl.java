package mk.finki.ukim.wp.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.finki.ukim.wp.lab.bootstrap.DataHolder;
import mk.finki.ukim.wp.lab.model.Author;
import mk.finki.ukim.wp.lab.repository.jpa.AuthorRepository;
import mk.finki.ukim.wp.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public void transferInMemoryToDataBase() {
        List<Author> inMemoryAuthors = DataHolder.authors;
        inMemoryAuthors.forEach(inMemoryAuthor -> {
            Author author = new Author();
            author.setName(inMemoryAuthor.getName());
            author.setSurname(inMemoryAuthor.getSurname());
            author.setBiography(inMemoryAuthor.getBiography());
            author.setDateOfBirth(inMemoryAuthor.getDateOfBirth());
            authorRepository.save(author);
        });
    }
    @Override
    public List<Author> listAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
