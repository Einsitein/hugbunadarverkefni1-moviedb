package is.hi.moviedb.service;

import is.hi.moviedb.model.Review;
import java.util.List;
public interface ReviewService {

    //Hálparföll
    // All the methods that ReviewServiceImpl will implement
    Review createReview(long userId,long mediaId,String mediaReview,double rating);
    Review createSeasonReview(long userId,long seasonId,String seasonReview,double rating);
    Review createTvShowReview(long userId,long tvShowId,String tvShowReview,double rating);
    boolean deleteReview(String id);
    Review changeReview(long userId,long mediaId,String mediaReview,double rating);
    Review findByUserIdAndMediaId(long userId,long mediaId);
    Review findById(String id);
    List<Review> findByUserId(long userId);
    List<Review> findByMediaId(long mediaId);
    List<Review> findAll();
    boolean deleteAll();
    double findRatingById(String id);
    double calculateAverageRating(List<Review> reviews);
}
