package is.hi.moviedb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reviews") //Specify table name in database
public class Review {

    /** id is a String that is made up of userId and movieId with a '-' inbetween
     *  Example: userId = 10, movieId = 99, then id = "10-99"
     * */
    @Id
    private String id;
    private Long userId;
    private Long mediaId;
    private String mediaReview;
    private double rating;

    /** Empty constructor */
    public Review(){
    }

    /** Constructor */
    public Review(Long userId,Long mediaId,String mediaReview,double rating) {
        this.id = String.valueOf(userId) + "-" + String.valueOf(mediaId);
        this.userId = userId;
        this.mediaId = mediaId;
        this.mediaReview = mediaReview;
        this.rating = rating;
    }


    /** Getters and setters */
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

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public String getMediaReview() {
        return mediaReview;
    }

    public void setMovieReview(String movieReview) {
        this.mediaReview = mediaReview;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
