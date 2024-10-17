package is.hi.moviedb.service;

import is.hi.moviedb.model.Review;
import java.util.List;
public interface ReviewService {
    Review createReview(Long userId,Long movieId,String movieReview,double rating);
    boolean deleteReview(String id);
    Review changeReview(Long userId,Long movieId,String movieReview,double rating);

    Review findById(long userId,long movieId);
    /*
    List<Review> findByUserid(long userId);
    List<Review> findByMovieid(long movieId);
    List<Review> findAll();
     */
}