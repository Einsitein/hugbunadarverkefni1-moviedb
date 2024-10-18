# MovieDB - Verkefni 3

## My Account Page
- [ ] **GET /me**: See my user account [1]
- [ ] **DELETE /me**: Delete user account [2]
- [ ] **GET /movies/ratings**: Retrieve all my movie ratings [1]
- [ ] **DELETE /movies/ratings**: Delete all movie ratings [2]

## All Users Page
- [ ] **GET /users**: List all users [1]

    ### Single User Page
    - [ ] **GET /users/{id}/movies/ratings**: See a user’s movie ratings [2]

## Movies Page
- [X] **GET /movies**: Retrieve all movies [1]

    ### Search Movies Page
    - [X] **GET /movies/{searchstring}**: Retrieve all movies that match the searchstring [1]

    ### Single Movie Page
    - [X] **GET /movies/{id}**: Retrieve a single movie info by ID [1]
    - [ ] **GET /movies/{id}/rating/avg**: Retrieve all users’ avg rating for a specific movie [3]
    - [X] **POST /movies/{id}/rating**: Rate a movie [2]
    - [ ] **GET /movies/{id}/rating**: Retrieve a single movie rating [1]
    - [X] **DELETE /movies/{id}/rating**: Delete a single movie rating [2]
    - [X] **PATCH /movies/{id}/rating**: Change a single movie rating [1]
