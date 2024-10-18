package is.hi.moviedb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import java.util.Arrays;
import java.util.List;

/**
 * Entity class representing a Movie in the movie database.
 * Implements the {@link Media} interface to provide common media attributes.
 */
@Entity
@Table(name = "movies")
public class Movie implements Media {

    /**
     * The unique identifier for the movie.
     */
    @Id
    private int id;

    /**
     * The name/title of the movie.
     */
    private String name;

    /**
     * The release year of the movie.
     */
    private int year;

    /**
     * The list of cast members in the movie.
     */
    @ElementCollection
    @CollectionTable(name = "movie_cast", joinColumns = @JoinColumn(name = "movie_id"))
    @Column(name = "cast_member")
    private List<String> cast;

    /**
     * The image associated with the movie.
     */
    private String images;

    /**
     * The type of media, e.g., "movie" or "show".
     */
    private String type;

    /**
     * The runtime of the movie in minutes.
     */
    private int runtime;

    /**
     * The director of the movie.
     */
    private String director;

    /**
     * The rating of the movie.
     */
    private double rating; // Changed from int to double

    /**
     * Default constructor for JPA.
     */
    public Movie() {
    }

    /**
     * Parameterized constructor to create a new Movie instance.
     *
     * @param id        the unique identifier of the movie
     * @param name      the name/title of the movie
     * @param year      the release year of the movie
     * @param cast      the list of cast members in the movie
     * @param images    the image associated with the movie
     * @param type      the type of media (e.g., "movie" or "show")
     * @param runtime   the runtime of the movie in minutes
     * @param director  the director of the movie
     * @param rating    the rating of the movie
     */
    public Movie(int id, String name, int year, List<String> cast, String images, String type, int runtime, String director, double rating) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.cast = cast;
        this.images = images;
        this.type = type;
        this.runtime = runtime;
        this.director = director;
        this.rating = rating;
    }

    /**
     * Retrieves the rating of the movie.
     *
     * @return the rating as a double
     */
    @Override
    public double getRating() {
        return rating;
    }

    /**
     * Sets the rating of the movie.
     *
     * @param rating the rating to set
     */
    @Override
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Retrieves the name/title of the movie.
     *
     * @return the name of the movie
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the name/title of the movie.
     *
     * @param name the name to set
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the release year of the movie.
     *
     * @return the release year as an integer
     */
    @Override
    public int getYear() {
        return year;
    }

    /**
     * Sets the release year of the movie.
     *
     * @param year the release year to set
     */
    @Override
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Retrieves the cast members of the movie.
     *
     * @return an array of cast member names
     */
    @Override
    public String[] getCast() {
        return cast.toArray(new String[0]);
    }

    /**
     * Sets the cast members of the movie.
     *
     * @param castArray an array of cast member names to set
     */
    @Override
    public void setCast(String[] castArray) {
        this.cast = Arrays.asList(castArray);
    }

    /**
     * Retrieves the image associated with the movie.
     *
     * @return the image as a string
     */
    @Override
    public String getImages() {
        return images;
    }

    /**
     * Sets the image associated with the movie.
     *
     * @param images the image to set
     */
    @Override
    public void setImages(String images) {
        this.images = images;
    }

    /**
     * Retrieves the type of media.
     *
     * @return the type as a string
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * Sets the type of media.
     *
     * @param type the type to set
     */
    @Override
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Retrieves the runtime of the movie in minutes.
     *
     * @return the runtime as an integer
     */
    @Override
    public int getRuntime() {
        return runtime;
    }

    /**
     * Sets the runtime of the movie.
     *
     * @param runtime the runtime in minutes to set
     */
    @Override
    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    /**
     * Retrieves the unique identifier of the movie.
     *
     * @return the movie ID as an integer
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the movie.
     *
     * @param id the movie ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the director of the movie.
     *
     * @return the director's name as a string
     */
    public String getDirector() {
        return director;
    }

    /**
     * Sets the director of the movie.
     *
     * @param director the director's name to set
     */
    public void setDirector(String director) {
        this.director = director;
    }
}
