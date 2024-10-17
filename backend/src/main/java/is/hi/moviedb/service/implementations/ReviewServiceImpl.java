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
    public Review createReview(long userId,long movieId,String movieReview,double rating){
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


    public Review findByUserIdAndMovieId(long userId,long movieId){
        Review review = reviewRepository.findByUserIdAndMovieId(userId,movieId);
        if (review != null){
            return review;
        }
        return null;
    }

    public Review findById(String id){
        Review review = reviewRepository.findById(id);
        if (review != null){
            return review;
        }
        return null;
    }
    public List<Review> findByUserId(long userId){
        List<Review> reviews = reviewRepository.findByUserId(userId);
        if (reviews != null){
            return reviews;
        }
        return null;
    }
    public List<Review> findByMovieId(long movieId){
        List<Review> reviews = reviewRepository.findByMovieId(movieId);
        if (reviews != null){
            return reviews;
        }
        return null;
    }
    public List<Review> findAll(){
        List<Review> reviews = reviewRepository.findAll();
        if (reviews != null){
            return reviews;
        }
        return null;
    };
}