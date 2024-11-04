package is.hi.moviedb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "tv_show")
public class TvShow {

    @Id
    private int id;

    private String name;
    private String description;
    private String genre;
    private String images;
    private String type;
    private String creators;
    private Double rating;

    // Constructors
    public TvShow() {}

    public TvShow(int id, String name, String description, String genre, String images, String type, String creators, Double rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.images = images;
        this.type = type;
        this.creators = creators;
        this.rating = rating;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getImages() { return images; }
    public void setImages(String images) { this.images = images; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getCreators() { return creators; }
    public void setCreators(String creators) { this.creators = creators; }

    public Double getRating() { return rating != null ? rating : 0.0; }
    public void setRating(Double rating) { this.rating = rating; }
}
