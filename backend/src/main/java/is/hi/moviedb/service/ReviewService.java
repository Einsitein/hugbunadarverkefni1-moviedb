package is.hi.moviedb.service;

import is.hi.moviedb.model.Review;
import java.util.List;
public interface ReviewService {

    //Hálparföll
    // All the methods that ReviewServiceImpl will implement
    Review createReview(int userId,int mediaId,String mediaReview,double rating);
    Review createSeasonReview(int userId,int seasonId,String seasonReview,double rating);
    Review createTvShowReview(int userId,int tvShowId,String tvShowReview,double rating);
    boolean deleteReview(String id);
    Review changeReview(int userId,int mediaId,String mediaReview,double rating);
    Review findByUserIdAndMediaId(int userId,int mediaId);
    Review findById(String id);
    List<Review> findByUserId(int userId);
    List<Review> findByMediaId(int mediaId);
    List<Review> findAll();
    boolean deleteAll();
    double findRatingById(String id);
    double calculateAverageRating(List<Review> reviews);
}
