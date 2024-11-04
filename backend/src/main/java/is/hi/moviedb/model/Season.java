package is.hi.moviedb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "season")
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int seasonNumber;
    private Double rating;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false) // Correct foreign key mapping
    private TvShow tvShow;

    // Constructors
    public Season() {}

    public Season(int seasonNumber, Double rating, TvShow tvShow) {
        this.seasonNumber = seasonNumber;
        this.rating = rating;
        this.tvShow = tvShow;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getSeasonNumber() { return seasonNumber; }
    public void setSeasonNumber(int seasonNumber) { this.seasonNumber = seasonNumber; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public TvShow getTvShow() { return tvShow; }
    public void setTvShow(TvShow tvShow) { this.tvShow = tvShow; }
}
