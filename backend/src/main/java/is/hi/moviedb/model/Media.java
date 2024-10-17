package is.hi.moviedb.model;

/**
 * Interface representing common media properties and behaviors.
 * Provides getter and setter methods for media attributes such as rating, name, year, cast, images, type, and runtime.
 */
public interface Media {

    /**
     * Gets the rating of the media.
     *
     * @return the rating as a double
     */
    double getRating();

    /**
     * Sets the rating of the media.
     *
     * @param rating the rating to set
     */
    void setRating(double rating);

    /**
     * Gets the name of the media.
     *
     * @return the name of the media
     */
    String getName();

    /**
     * Sets the name of the media.
     *
     * @param name the name to set
     */
    void setName(String name);

    /**
     * Gets the release year of the media.
     *
     * @return the year as an integer
     */
    int getYear();

    /**
     * Sets the release year of the media.
     *
     * @param year the year to set
     */
    void setYear(int year);

    /**
     * Gets the cast members of the media.
     *
     * @return an array of cast member names
     */
    String[] getCast();

    /**
     * Sets the cast members of the media.
     *
     * @param cast an array of cast member names to set
     */
    void setCast(String[] cast);

    /**
     * Gets the images associated with the media.
     *
     * @return a string representing the images
     */
    String getImages();

    /**
     * Sets the images associated with the media.
     *
     * @param images the images to set
     */
    void setImages(String images);

    /**
     * Gets the type of the media (e.g., movie, show).
     *
     * @return the type as a string
     */
    String getType();

    /**
     * Sets the type of the media.
     *
     * @param type the type to set
     */
    void setType(String type);

    /**
     * Gets the runtime of the media in minutes.
     *
     * @return the runtime as an integer
     */
    int getRuntime();

    /**
     * Sets the runtime of the media.
     *
     * @param runtime the runtime in minutes to set
     */
    void setRuntime(int runtime);
}
