package is.hi.moviedb.controller;

import is.hi.moviedb.model.Review;
import is.hi.moviedb.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping(value = "/createreview")
    public ResponseEntity<Review> createReview(@RequestBody Review review){
        Review newReview = reviewService.createReview(review.getUserId(), review.getMovieId(), review.getMovieReview(), review.getRating());
        return ResponseEntity.ok(newReview);
    }

    @DeleteMapping(value = "/deletereview/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable String id){
        boolean isDeleted = reviewService.deleteReview(id);

        if (isDeleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @PatchMapping(value = "/changereview")
    public ResponseEntity<Review> changeReview(@RequestBody Review review){
        if (reviewService.findById(review.getUserId(),review.getMovieId()) != null) {
            Review updatedReview = reviewService.createReview(review.getUserId(), review.getMovieId(), review.getMovieReview(), review.getRating());
            return ResponseEntity.ok(updatedReview); // Found and changed
        } else {
            return ResponseEntity.notFound().build(); // 404 Not found
        }
    }
}