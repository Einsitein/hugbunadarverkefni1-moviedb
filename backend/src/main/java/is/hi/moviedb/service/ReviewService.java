package is.hi.moviedb.service;

import is.hi.moviedb.model.Review;
import java.util.List;
public interface ReviewService {

    //Hálparföll
    // All the methods that ReviewServiceImpl will implement
    Review createReview(long userId,long movieId,String movieReview,double rating);
    Review createSeasonReview(long userId,long seasonId,String seasonReview,double rating);
    Review createTvShowReview(long userId,long tvShowId,String tvShowReview,double rating);
    boolean deleteReview(String id);
    boolean deleteSeasonReview(long userId,long seasonId);
    boolean deleteTvShowReview(long userId,long tvShowId);
    Review changeReview(long userId,long movieId,String movieReview,double rating);
    Review changeSeasonReview(long userId,long seasonId,String seasonReview,double rating);
    Review changeTvShowReview(long userId,long tvShowId,String tvShowReview,double rating);

    Review findByUserIdAndMovieId(long userId,long movieId);
    Review findById(String id);
    List<Review> findByUserId(long userId);
    List<Review> findByMovieId(long movieId);
    List<Review> findAll();
    boolean deleteAll();
    double findRatingById(String id);

    double calculateAverageRating(List<Review> reviews);
}
