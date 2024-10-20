# MovieDB - Verkefni 3

## User Endpoints
- [X] **GET /user/me**: Retrieves the current authenticated user's information using an access token.
- [X] **GET /user/users**: Retrieves a list of all users.
- [X] **GET /user/users/{user_id}/movies/ratings**: Retrieves all movie ratings given by a specific user based on their user ID.
- [X] **POST /user/idbyemail**: Retrieves a user ID by providing an email.
- [X] **POST /user/register**: Registers a new user with email and password.
- [X] **POST /user/login**: Logs in a user with email and password.
- [X] **PATCH /user/password**: Changes a user's password using an access token.
- [X] **DELETE /user/me**: Deletes the currently authenticated user based on an access token.


## Movie Endpoints

- [X] **GET /movies**: Retrieves a list of all movies.
- [X] **GET /movies/{id}**: Retrieves a movie by its unique ID.
- [X] **GET /movies/search/{searchString}**: Searches for movies by a case-insensitive search string in their name.

## Review Endpoints
- [X] **GET /review/findById/{id}**: Finds a review by its ID.
- [X] **GET /review/findByUserIdAndMovieId/{userId}/{movieId}**: Finds a review by the user ID and movie ID combination.
- [X] **GET /review/findByUserId/{userId}**: Retrieves all reviews written by a specific user.
- [X] **GET /review/findByMovieId/{movieId}**: Retrieves all reviews for a specific movie.
- [X] **GET /review/findAll**: Retrieves all reviews in the system.
- [X] **GET /review/findRating/{id}**: Retrieves the rating of a review by its ID.
- [X] **GET /review/findAverageRatingByMovieId/{movieId}**: Retrieves the average rating for a particular movie based on all reviews.
- [X] **POST /review/createReview**: Creates a new review object with a user ID, movie ID, review text, and rating.
- [X] **DELETE /review/deleteReview/{id}**: Deletes a review by its ID.
- [X] **DELETE /review/deleteAll**: Deletes all reviews in the system.
- [X] **PATCH /review/changeReview**: Updates an existing review's content and rating.



    
    

    
