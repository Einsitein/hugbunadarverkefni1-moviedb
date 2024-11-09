package is.hi.moviedb.repository;

import is.hi.moviedb.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long>{
    //Custom query
    Review findByUserIdAndMediaId(long userId,long mediaId);
    Review findById(String id);
    boolean existsByUserIdAndMediaId(long userId,long mediaId);
    boolean existsById(String id);
    void deleteById(String id);
    List<Review> findAll();
    List<Review> findByUserId(Long userId);
    List<Review> findByMediaId(Long mediaId);
    double findRatingById(String id);
    void deleteAll();
    double findAverageRatingByMediaId(long mediaId);
}
