package is.hi.moviedb.service;

import is.hi.moviedb.model.Review;
import java.util.List;
public interface ReviewService {

    // All the methods that ReviewServiceImpl will implement
    Review createReview(long userId,long movieId,String movieReview,double rating);
    boolean deleteReview(String id);
    Review changeReview(long userId,long movieId,String movieReview,double rating);

    Review findByUserIdAndMovieId(long userId,long movieId);
    Review findById(String id);
    List<Review> findByUserId(long userId);
    List<Review> findByMovieId(long movieId);
    List<Review> findAll();
    boolean deleteAll();
    double findRatingById(String id);
    double findAverageRatingByMovieId(long movieId);
}