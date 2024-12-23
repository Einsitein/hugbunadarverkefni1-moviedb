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
     * Creates new Review object
     * @param Input is Json format Review object (long userId, long movieId, String review, double rating)
     * @return Newly created Review object
     */
    @PostMapping(value = "/createReview")
    public ResponseEntity<Review> createReview(@RequestBody Review review){
        Review newReview = reviewService.createReview(review.getUserId(), review.getMovieId(), review.getMovieReview(), review.getRating()); // Assuming this returns a Review
        if (review != null) { // Check if successfully created
            return ResponseEntity.ok(review); // Return the rating with 200 OK status
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not found
        }
    }


    @PostMapping(value = "/createSeasonReview")
    public ResponseEntity<Review> createSeasonReview(@RequestBody Review review){
        Review newReview = reviewService.createSeasonReview(review.getUserId(), review.getMovieId(), review.getMovieReview(), review.getRating()); // Assuming this returns a Review
        if (review != null) { // Check if successfully created
            return ResponseEntity.ok(review); // Return the rating with 200 OK status
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not found
        }
    }

    @PostMapping(value = "/createTvShowReview")
    public ResponseEntity<Review> createTvShowReview(@RequestBody Review review){
        Review newReview = reviewService.createTvShowReview(review.getUserId(), review.getMovieId(), review.getMovieReview(), review.getRating()); // Assuming this returns a Review
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
     * Deletes a Review object that has userId and movieId
     * @param userId
     * @param movieId
     * @return 202 if success, 404 if not found
     */

    @DeleteMapping(value = "/deleteSeasonReview/{userId}/{seasonId}")
    public ResponseEntity<Void> deleteSeasonReview(@PathVariable long userId, @PathVariable long seasonId){
        boolean isDeleted = reviewService.deleteSeasonReview(userId, seasonId); // Assuming this returns a boolean
        if (isDeleted) { // Check if successfully deleted
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    /**
     * Deletes a Review object that has userId and movieId
     * @param userId
     * @param movieId
     * @return 202 if success, 404 if not found
     */
    @DeleteMapping(value = "/deleteTvShowReview/{userId}/{tvShowId}")
    public ResponseEntity<Void> deleteTvShowReview(@PathVariable long userId, @PathVariable long tvShowId){
        boolean isDeleted = reviewService.deleteTvShowReview(userId, tvShowId); // Assuming this returns a boolean
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
        Review updatedReview = reviewService.changeReview(review.getUserId(), review.getMovieId(), review.getMovieReview(), review.getRating()); // Assuming this returns a Review
        if (updatedReview != null) { // Check if successfully changed
            return ResponseEntity.ok(updatedReview); // Found and changed
        } else {
            return ResponseEntity.notFound().build(); // 404 Not found
        }
    }

    /**
     * Changes a review for a Season
     * @param review object
     * @return Review object that was changed, 404 if not found
     */

    @PatchMapping(value = "/changeSeasonReview")
    public ResponseEntity<Review> changeSeasonReview(@RequestBody Review review){
        Review updatedReview = reviewService.changeSeasonReview(review.getUserId(), review.getMovieId(), review.getMovieReview(), review.getRating()); // Assuming this returns a Review
        if (updatedReview != null) { // Check if successfully changed
            return ResponseEntity.ok(updatedReview); // Found and changed
        } else {
            return ResponseEntity.notFound().build(); // 404 Not found
        }
    }

    /**
     * Changes a review for a TvShow
        * @param review object
        * @return Review object that was changed, 404 if not found
     */

    @PatchMapping(value = "/changeTvShowReview")
    public ResponseEntity<Review> changeTvShowReview(@RequestBody Review review){
        Review updatedReview = reviewService.changeTvShowReview(review.getUserId(), review.getMovieId(), review.getMovieReview(), review.getRating()); // Assuming this returns a Review
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
    @GetMapping(value = "/findByUserIdAndMovieId/{userId}/{movieId}")
    public ResponseEntity<Review> findByUserIdAndMovieId (@PathVariable long userId, @PathVariable long movieId){
        Review review = reviewService.findByUserIdAndMovieId(userId,movieId); // Assuming this returns a Review
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
    @GetMapping(value = "/findByMovieId/{movieId}")
    public ResponseEntity<List<Review>> findByMovieId(@PathVariable long movieId){
        List<Review> reviews = reviewService.findByMovieId(movieId); // Assuming this returns List<Review>
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
    @GetMapping(value = "/findAverageRatingByMovieId/{movieId}")
    public ResponseEntity<Double> findAverageRatingByMovieId(
        @PathVariable long movieId
    ) {
        List<Review> reviews = reviewService.findByMovieId(movieId);
        if(reviews == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        double result = reviewService.calculateAverageRating(reviews); 
        if (result < 0.0) { 
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } 

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
