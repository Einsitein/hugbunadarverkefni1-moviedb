package is.hi.moviedb.controller;

import is.hi.moviedb.model.Review;
import is.hi.moviedb.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping(value = "/createReview")
    public ResponseEntity<Review> createReview(@RequestBody Review review){
        Review newReview = reviewService.createReview(review.getUserId(), review.getMovieId(), review.getMovieReview(), review.getRating());
        if (review != null) {
            return ResponseEntity.ok(review);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping(value = "/deleteReview/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable String id){
        boolean isDeleted = reviewService.deleteReview(id);

        if (isDeleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @PatchMapping(value = "/changeReview")
    public ResponseEntity<Review> changeReview(@RequestBody Review review){
        Review updatedReview = reviewService.changeReview(review.getUserId(), review.getMovieId(), review.getMovieReview(), review.getRating());
        if (updatedReview != null) {
            return ResponseEntity.ok(updatedReview); // Found and changed
        } else {
            return ResponseEntity.notFound().build(); // 404 Not found
        }
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<Review> findByUserIdAndMovieId(@PathVariable String id){
        Review review = reviewService.findById(id);
        if (review != null) {
            return ResponseEntity.ok(review);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping(value = "/findByUserIdAndMovieId/{userId}/{movieId}")
    public ResponseEntity<Review> findByUserIdAndMovieId (@PathVariable long userId, @PathVariable long movieId){
        Review review = reviewService.findByUserIdAndMovieId(userId,movieId);
        if (review != null) {
            return ResponseEntity.ok(review);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value = "/findByUserId/{userId}")
    public ResponseEntity<List<Review>> findByUserId(@PathVariable long userId){
        List<Review> reviews = reviewService.findByUserId(userId);
        if (!reviews.isEmpty()) {
            return ResponseEntity.ok(reviews);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping(value = "/findByMovieId/{movieId}")
    public ResponseEntity<List<Review>> findByMovieId(@PathVariable long movieId){
        List<Review> reviews = reviewService.findByMovieId(movieId);
        if (!reviews.isEmpty()) {
            return ResponseEntity.ok(reviews);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<List<Review>> findAll(){
        List<Review> reviews = reviewService.findAll();
        if (!reviews.isEmpty()) {
            return ResponseEntity.ok(reviews);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping(value = "/deleteAll")
    public ResponseEntity<Void> deleteAll(){
        boolean isDeleted = reviewService.deleteAll();
        if (isDeleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @GetMapping(value = "/findRating/{id}")
    public ResponseEntity<Double> findRatingById(@PathVariable String id) {
        Double rating = reviewService.findRatingById(id); // Assuming this returns a Double

        if (rating != null && rating >= 0.0) { // Check for null and if rating is non-negative
            return ResponseEntity.ok(rating); // Return the rating with 200 OK status
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if not found
        }
    }

    @GetMapping(value = "/findAverageRatingByMovieId/{movieId}")
    public ResponseEntity<Double> findAverageRatingByMovieId(@PathVariable long movieId) {
        Double averageRating = reviewService.findAverageRatingByMovieId(movieId); // Assuming this returns a Double
        if (averageRating != null && averageRating >= 0.0) { // Check for null and if rating is non-negative
            return ResponseEntity.ok(averageRating); // Return the rating with 200 OK status
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if not found
        }
    }
}
