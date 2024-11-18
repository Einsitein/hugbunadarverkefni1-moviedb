package is.hi.moviedb.repository;

import is.hi.moviedb.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long>{
    //Custom query
    Review findByUserIdAndMediaId(int userId,int mediaId);
    Review findById(String id);
    boolean existsByUserIdAndMediaId(int userId,int mediaId);
    boolean existsById(String id);
    void deleteById(String id);
    List<Review> findAll();
    List<Review> findByUserId(int userId);
    List<Review> findByMediaId(int mediaId);
    double findRatingById(String id);
    void deleteAll();
    double findAverageRatingByMediaId(int mediaId);
}
