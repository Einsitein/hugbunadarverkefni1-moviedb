package is.hi.moviedb.service.implementations;

import org.springframework.transaction.annotation.Transactional;
import is.hi.moviedb.model.Review;
import is.hi.moviedb.security.JwtUtil;
import is.hi.moviedb.repository.ReviewRepository;
import is.hi.moviedb.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
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
    public Review createReview(long userId,long movieId,String movieReview,double rating){
        Review review = new Review(userId,movieId,movieReview,rating);
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
     * Finds the average rating a movie has gotten from users
     * @param movieId
     * @return Double averageRating, negetive number if not found
     */
    @Override
    public double findAverageRatingByMovieId(long movieId){
        List<Review> reviews = findByMovieId(movieId);
        double total = 0.0;
        int count = 0;
        for (Review review : reviews) {
            total += review.getRating();
        }
        count = reviews.size();
        if (0 < count) {
            return total/count;
        } else {
            return -1.0;
        }
    }
}