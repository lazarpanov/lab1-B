package mk.finki.ukim.wp.lab.repository.jpa;

import mk.finki.ukim.wp.lab.model.Book;
import mk.finki.ukim.wp.lab.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByBook(Book book);
    List<Review> findReviewByTimestampBetween(LocalDateTime from, LocalDateTime to);
    List<Review> findReviewByTimestampBetweenAndBook(LocalDateTime from, LocalDateTime to, Book book);
}
