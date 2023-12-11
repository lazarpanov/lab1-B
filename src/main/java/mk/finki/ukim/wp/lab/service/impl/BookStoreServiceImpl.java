package mk.finki.ukim.wp.lab.service.impl;

import jakarta.transaction.Transactional;
import mk.finki.ukim.wp.lab.bootstrap.DataHolder;
import mk.finki.ukim.wp.lab.model.BookStore;
import mk.finki.ukim.wp.lab.repository.jpa.BookStoreRepository;
import mk.finki.ukim.wp.lab.service.BookStoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookStoreServiceImpl implements BookStoreService {
    private final BookStoreRepository bookStoreRepository;

    public BookStoreServiceImpl(BookStoreRepository bookStoreRepository) {
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public List<BookStore> findAll() {
        return bookStoreRepository.findAll();
    }

    @Override
    public BookStore findById(Long id) {
        return bookStoreRepository.findById(id).orElseThrow(() -> new RuntimeException("book store not found"));
    }

    @Override
    public BookStore findByName(String name) {
        return bookStoreRepository.findBookStoreByName(name);
    }

    @Override
    @Transactional
    public void transferInMemoryToDataBase() {
        List<BookStore> inMemoryBookStores = DataHolder.bookStores;
        inMemoryBookStores.forEach(inMemoryBookStore -> {
            BookStore bookStore = new BookStore(inMemoryBookStore.getName(), inMemoryBookStore.getCity(), inMemoryBookStore.getAddress());
            bookStoreRepository.save(bookStore);
        });
    }
}
