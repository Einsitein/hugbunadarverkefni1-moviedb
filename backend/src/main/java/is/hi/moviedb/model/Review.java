package is.hi.moviedb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reviews") //Specify table name in database
public class Review {

    @Id
    private String id;
    private Long userId;
    private Long movieId;
    private String movieReview;
    private double rating;

    //Constructor
    public Review(){
    }

    public Review(Long userId,Long movieId,String movieReview,double rating) {
        this.id = String.valueOf(userId) + String.valueOf(movieId);
        this.userId = userId;
        this.movieId = movieId;
        this.movieReview = movieReview;
        this.rating = rating;
    }


    public String getId() {
        return id;
    }

    public void setId(String combinedId) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getMovieReview() {
        return movieReview;
    }

    public void setMovieReview(String movieReview) {
        this.movieReview = movieReview;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}