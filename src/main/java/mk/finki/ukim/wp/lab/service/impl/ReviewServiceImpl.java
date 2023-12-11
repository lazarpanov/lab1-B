package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.model.Review;
import mk.finki.ukim.wp.lab.repository.jpa.ReviewRepository;
import mk.finki.ukim.wp.lab.service.ReviewService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void save(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public List<Review> findReviewByTimestampBetween(LocalDateTime from, LocalDateTime to) {
        return this.reviewRepository.findReviewByTimestampBetween(from, to);
    }

    @Override
    public List<Review> findReviewByTimestampBetweenAndBook(LocalDateTime from, LocalDateTime to, Book book) {
        return this.reviewRepository.findReviewByTimestampBetweenAndBook(from, to, book);
    }

    @Override
    public List<Review> findAllReviewsByBook(Book book) {
        return this.reviewRepository.findAllByBook(book);
    }
}
