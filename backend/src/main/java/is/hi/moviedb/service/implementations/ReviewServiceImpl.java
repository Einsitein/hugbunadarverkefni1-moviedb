package is.hi.moviedb.service.implementations;

import org.springframework.transaction.annotation.Transactional;
import is.hi.moviedb.model.Review;
import is.hi.moviedb.security.JwtUtil;
import is.hi.moviedb.repository.ReviewRepository;
import is.hi.moviedb.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import is.hi.moviedb.model.Episode;
import is.hi.moviedb.model.Season;
import is.hi.moviedb.service.EpisodeService;
import is.hi.moviedb.service.SeasonService;
import is.hi.moviedb.service.implementations.EpisodeServiceImpl;
import is.hi.moviedb.service.implementations.SeasonServiceImpl;
import is.hi.moviedb.repository.EpisodeRepository;
import is.hi.moviedb.repository.SeasonRepository;
import is.hi.moviedb.controller.TvShowController;
@Service
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;
    private final EpisodeService episodeService;
    private final SeasonService seasonService;  // Add the SeasonService

    // Inject both ReviewRepository and SeasonService via constructor

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, SeasonService seasonService, EpisodeService episodeService) {
        this.reviewRepository = reviewRepository;
        this.episodeService = episodeService;
        this.seasonService = seasonService;  // Initialize the seasonService
    }

    /**
     * Creates a new Review
     * @param userId
     * @param movieId
     * @param movieReview
     * @param rating
     * @return Review
     */
    @Override
    public Review createReview(
        long userId,
        long movieId,
        String movieReview,
        double rating
    ){
        Review review = new Review(userId,movieId,movieReview,rating);
        reviewRepository.save(review);
        return review;
    }

    @Override
    public Review createSeasonReview(long userId,long seasonId,String seasonReview,double rating){
        List<Episode> episodeList = episodeService.getEpisodesBySeasonId((int) seasonId);
        for (int i = 0; i < episodeList.size();i++) {
            long episodeId = episodeList.get(i).getId();
            if (findByUserIdAndMovieId(userId,episodeId) == null) {
                createReview(userId,episodeId,"",rating);
            }
        }
        Review review = new Review(userId,seasonId,seasonReview,rating);
        reviewRepository.save(review);
        return review;
    }

    @Override
    public Review createTvShowReview(long userId,long tvShowId,String tvShowReview,double rating){
        List<Season> seasonList = seasonService.getSeasonsByTvShowId((int) tvShowId);
        for (int i = 0; i < seasonList.size(); i++) {
            long seasonId = seasonList.get(i).getId();
            if (findByUserIdAndMovieId(userId,seasonId) == null) {
                createSeasonReview(userId,seasonId,"",rating);
            }
        }
        Review review = new Review(userId,tvShowId,tvShowReview,rating);
        reviewRepository.save(review);
        return review;
    }

    /**
     * Deletes Review with id
     * @param id
     * @return true if successfull, false if not
     */

    @Transactional
    public boolean deleteReview(String id){
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true; // Successfully deleted
        }
        return false; // Review not found
    }

    /**
     * Changes a particular Review
     * @param userId
     * @param movieId
     * @param movieReview
     * @param rating
     * @return Review
     */

    @Override
    public Review changeReview(long userId,long movieId,String movieReview,double rating){
        Review review = reviewRepository.findById(Long.toString(userId) + "-" + Long.toString(movieId));
        review.setRating(rating);
        review.setMovieReview(movieReview);
        reviewRepository.save(review);
        if (review != null){
            return review;
        }
        return null;
    };

    /**
     * Finds a review with userId and movieId
     * @param userId
     * @param movieId
     * @return Review
     */
    @Override
    public Review findByUserIdAndMovieId(long userId,long movieId){
        Review review = reviewRepository.findByUserIdAndMovieId(userId,movieId);
        if (review != null){
            return review;
        }
        return null;
    }

    /**
     * Finds a Review by id
     * @param id
     * @return Review
     */
    @Override
    public Review findById(String id){
        Review review = reviewRepository.findById(id);
        if (review != null){
            return review;
        }
        return null;
    }

    /**
     * Finds all reviews that a user has made
     * @param userId
     * @return List of Reviews
     */
    @Override
    public List<Review> findByUserId(long userId){
        List<Review> reviews = reviewRepository.findByUserId(userId);
        if (reviews != null){
            return reviews;
        }
        return null;
    }

    /**
     * Finds all reviews for a particular movie
     * @param movieId
     * @return List of Reviews
     */
    @Override
    public List<Review> findByMovieId(long movieId){
        List<Review> reviews = reviewRepository.findByMovieId(movieId);
        if (reviews != null){
            return reviews;
        }
        return null;
    }

    /**
     * Finds all Reviews
     * @return List of Reviews
     */
    @Override
    public List<Review> findAll(){
        List<Review> reviews = reviewRepository.findAll();
        if (reviews != null){
            return reviews;
        }
        return null;
    };

    /**
     * Deletes all Reviews
     * @return true if successfull, false if not
     */

    @Override
    public boolean deleteAll(){
        if (reviewRepository.findAll() != null) {
            reviewRepository.deleteAll();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Finds the rating for a Review
     * @param id
     * @return Double rating, negetive if not found
     */
    @Override
    public double findRatingById(String id){
        Review review = findById(id);
        if (review != null){
            return review.getRating();
        } else {
            return -1.0;
        }
    }

    /**
     * Calculates the average rating from a list of reviews
     * @param List<Review> reviews
     * @return Double averageRating, negetive number if not found
     */
    @Override
    public double calculateAverageRating(List<Review> reviews){
        return reviews.stream().mapToDouble(review -> review.getRating()).average().orElse(-1.0); 
    }
}
