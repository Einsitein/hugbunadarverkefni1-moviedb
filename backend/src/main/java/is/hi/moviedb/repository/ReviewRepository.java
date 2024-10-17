package is.hi.moviedb.repository;

import is.hi.moviedb.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ReviewRepository extends JpaRepository<Review,Long>{
    //Custom query
    Review findById(String id);
    boolean existsById(String id);
    void deleteById(String id);
}