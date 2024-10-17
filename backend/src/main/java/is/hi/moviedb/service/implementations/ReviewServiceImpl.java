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

    @Override
    public Review createReview(Long userId,Long movieId,String movieReview,double rating){
        Review review = new Review(userId,movieId,movieReview,rating);
        reviewRepository.save(review);
        return review;
    }

    @Transactional
    public boolean deleteReview(String id){
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true; // Successfully deleted
        }
        return false; // Review not found
    }

    @Override
    public Review changeReview(Long userId,Long movieId,String movieReview,double rating){
        Review review = reviewRepository.findById(Long.toString(userId) + Long.toString(movieId));
        review.setRating(rating);
        review.setMovieReview(movieReview);
        reviewRepository.save(review);
        return review;
    };


    public Review findById(long userId,long movieId){
        Review review = reviewRepository.findById(String.valueOf(userId) + String.valueOf(movieId));
        if (review != null){
            return review;
        }
        return null;
    }
    /*
    public List<Review> findByUserid(long userId){
        return null;
    };
    public List<Review> findByMovieid(long movieId){
        return null;
    };
    public List<Review> findAll(){
        return null;
    };

     */

}