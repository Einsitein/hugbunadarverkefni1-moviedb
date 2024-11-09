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

    /**
     * Creates new Review object for a movie or episode
     * @param Input is Json format Review object (long userId, long movieId, String review, double rating)
     * @return Newly created Review object
     */
    @PostMapping(value = "/createReview")
    public ResponseEntity<Review> createReview(@RequestBody Review review){
        Review newReview = reviewService.createReview(review.getUserId(), review.getMediaId(), review.getMediaReview(), review.getRating()); // Assuming this returns a Review
        if (review != null) { // Check if successfully created
            return ResponseEntity.ok(review); // Return the rating with 200 OK status
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not found
        }
    }

    /**
     * Creates new Review object for a season, inherits to episodes in that season if they are not alredy rated.
     * @param Input is Json format Review object (long userId, long movieId, String review, double rating)
     * @return Newly created Review object
     */

    @PostMapping(value = "/createSeasonReview")
    public ResponseEntity<Review> createSeasonReview(@RequestBody Review review){
        Review newReview = reviewService.createSeasonReview(review.getUserId(), review.getMediaId(), review.getMediaReview(), review.getRating()); // Assuming this returns a Review
        if (review != null) { // Check if successfully created
            return ResponseEntity.ok(review); // Return the rating with 200 OK status
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not found
        }
    }

    /**
     * Creates new Review object for a tv show, inherits to seasons of that tv show if they are not alredy rated.
     * @param Input is Json format Review object (long userId, long movieId, String review, double rating)
     * @return Newly created Review object
     */
    @PostMapping(value = "/createTvShowReview")
    public ResponseEntity<Review> createTvShowReview(@RequestBody Review review){
        Review newReview = reviewService.createTvShowReview(review.getUserId(), review.getMediaId(), review.getMediaReview(), review.getRating()); // Assuming this returns a Review
        if (review != null) { // Check if successfully created
            return ResponseEntity.ok(review); // Return the rating with 200 OK status
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not found
        }
    }

    /**
     * Deletes a Review object that has the input id
     * @param String id
     * @return 202 if success, 404 if not found
     */
    @DeleteMapping(value = "/deleteReview/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable String id){
        boolean isDeleted = reviewService.deleteReview(id); // Assuming this returns a boolean
        if (isDeleted) { // Check if successfully deleted
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    /**
     * Changes a review
     * @param Review object
     * @return Review object that was changed, 404 if not found
     */
    @PatchMapping(value = "/changeReview")
    public ResponseEntity<Review> changeReview(@RequestBody Review review){
        Review updatedReview = reviewService.changeReview(review.getUserId(), review.getMediaId(), review.getMediaReview(), review.getRating()); // Assuming this returns a Review
        if (updatedReview != null) { // Check if successfully changed
            return ResponseEntity.ok(updatedReview); // Found and changed
        } else {
            return ResponseEntity.notFound().build(); // 404 Not found
        }
    }

    /**
     * Finds Review object with id
     * @param String id
     * @return Review object
     */
    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<Review> findByUserIdAndMovieId(@PathVariable String id){
        Review review = reviewService.findById(id); // Assuming this returns a Review
        if (review != null) { // Check if found
            return ResponseEntity.ok(review); // Return the rating with 200 OK status
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not found
        }
    }

    /**
     * Finds and returns object that has userId and movieId
     * @param userId
     * @param movieId
     * @return Review object if found, 404 if not
     */
    @GetMapping(value = "/findByUserIdAndMediaId/{userId}/{movieId}")
    public ResponseEntity<Review> findByUserIdAndMovieId (@PathVariable long userId, @PathVariable long mediaId){
        Review review = reviewService.findByUserIdAndMediaId(userId,mediaId); // Assuming this returns a Review
        if (review != null) { // Check if found
            return ResponseEntity.ok(review); // Return the rating with 200 OK status
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not found
        }
    }

    /**
     * Finds and returns Review by id
     * @param userId
     * @return Review object if fould, 404 if not
     */
    @GetMapping(value = "/findByUserId/{userId}")
    public ResponseEntity<List<Review>> findByUserId(@PathVariable long userId){
        List<Review> reviews = reviewService.findByUserId(userId); // Assuming this returns List<Review>
        if (!reviews.isEmpty()) { // Check if found
            return ResponseEntity.ok(reviews); // Return the rating with 200 OK status
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not found
        }
    }

    /**
     * Finds and returns all Reviews for a particular movie
     * @param movieId
     * @return List of Review
     */
    @GetMapping(value = "/findByMediaId/{mediaId}")
    public ResponseEntity<List<Review>> findByMediaId(@PathVariable long mediaId){
        List<Review> reviews = reviewService.findByMediaId(mediaId); // Assuming this returns List<Review>
        if (!reviews.isEmpty()) { // Check if found
            return ResponseEntity.ok(reviews); // Return the rating with 200 OK status
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Finds all Reviews
     * @return List of Reviews
     */

    @GetMapping(value = "/findAll")
    public ResponseEntity<List<Review>> findAll(){
        List<Review> reviews = reviewService.findAll(); // Assuming this returns a Review
        if (!reviews.isEmpty()) { // Check if found
            return ResponseEntity.ok(reviews); // Return the rating with 200 OK status
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Deletes all Reviews
     * @return 202 if successfull, 404 if not found
     */

    @DeleteMapping(value = "/deleteAll")
    public ResponseEntity<Void> deleteAll(){
        boolean isDeleted = reviewService.deleteAll(); // Assuming this returns a boolean
        if (isDeleted) { // Check if successfully deleted
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    /**
     * Finds a Rating for a Review that has id
     * @param id
     * @return Rating for that id
     */
    @GetMapping(value = "/findRating/{id}")
    public ResponseEntity<Double> findRatingById(@PathVariable String id) {
        Double rating = reviewService.findRatingById(id); // Assuming this returns a Double
        if (rating != null && rating >= 0.0) { // Check for null and if rating is non-negative
            return ResponseEntity.ok(rating); // Return the rating with 200 OK status
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if not found
        }
    }

    /**
     * Finds the average rating that user have given to a particular movie
     * @param movieId
     * @return Double averageRating
     */
    @GetMapping(value = "/findAverageRatingByMediaId/{mediaId}")
    public ResponseEntity<Double> findAverageRatingByMovieId(@PathVariable long mediaId) {
        Double averageRating = reviewService.findAverageRatingByMediaId(mediaId); // Assuming this returns a Double
        if (averageRating != null && averageRating >= 0.0) { // Check for null and if rating is non-negative
            return ResponseEntity.ok(averageRating); // Return the rating with 200 OK status
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if not found
        }
    }
}
