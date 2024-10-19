package is.hi.moviedb.repository;

import is.hi.moviedb.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long>{
    //Custom query
    Review findByUserIdAndMovieId(long userId,long movieId);
    Review findById(String id);
    boolean existsByUserIdAndMovieId(long userId,long movieId);
    boolean existsById(String id);
    void deleteById(String id);
    List<Review> findAll();
    List<Review> findByUserId(Long userId);
    List<Review> findByMovieId(Long movieId);
    double findRatingById(String id);
    void deleteAll();
    double findAverageRatingByMovieId(long movieId);
}
