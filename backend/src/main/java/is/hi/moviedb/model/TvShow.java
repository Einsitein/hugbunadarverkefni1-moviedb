package is.hi.moviedb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

/**
 * Entity class representing a TV show in the database.
 */
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

    // Constructors
    public TvShow() {}

    public TvShow(int id, String name, String description, String genre, String images, String type, String creators) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.images = images;
        this.type = type;
        this.creators = creators;
    }

    // Getters and setters
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
}
