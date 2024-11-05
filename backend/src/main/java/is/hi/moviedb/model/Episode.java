package is.hi.moviedb.model;

import jakarta.persistence.*;
import java.util.Date;


/**
 * Entity class representing an Episode in a Season.
 */
@Entity
@Table(name = "episode")
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int episodeNumber;
    private String episodeName;
    private String description;
    private Date releaseDate;
    private Double rating;

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    // Constructors
    public Episode() {}

    public Episode(int episodeNumber, String episodeName, String description, Date releaseDate, Double rating, Season season) {
        this.episodeNumber = episodeNumber;
        this.episodeName = episodeName;
        this.description = description;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.season = season;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getEpisodeNumber() { return episodeNumber; }
    public void setEpisodeNumber(int episodeNumber) { this.episodeNumber = episodeNumber; }

    public String getEpisodeName() { return episodeName; }
    public void setEpisodeName(String episodeName) { this.episodeName = episodeName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getReleaseDate() { return releaseDate; }
    public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public Season getSeason() { return season; }
    public void setSeason(Season season) { this.season = season; }
}
