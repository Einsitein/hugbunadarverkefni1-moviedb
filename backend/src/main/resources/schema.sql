------------------------Users------------------------

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

------------------------Movies------------------------

CREATE TABLE movies (
    id INT PRIMARY KEY,
    name VARCHAR(255),
    year INT,
    images VARCHAR(255),
    type VARCHAR(50),
    runtime INT,
    director VARCHAR(255)
);

CREATE TABLE movie_cast (
    movie_id INT,
    cast_member VARCHAR(255),
    FOREIGN KEY (movie_id) REFERENCES movies(id)
);

------------------------TvShow------------------------

CREATE TABLE IF NOT EXISTS tv_show (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    genre VARCHAR(100),
    images VARCHAR(255),
    type VARCHAR(50),  
    creators VARCHAR(255),
    rating DOUBLE PRECISION DEFAULT NULL  
);


CREATE TABLE IF NOT EXISTS season (
    id INT PRIMARY KEY,
    show_id INT NOT NULL,
    season_number INT NOT NULL,
    rating DOUBLE PRECISION DEFAULT NULL,
    FOREIGN KEY (show_id) REFERENCES tv_show(id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS episode (
    id INT PRIMARY KEY,
    season_id INT NOT NULL,
    episode_number INT NOT NULL,
    episode_name VARCHAR(255),
    description TEXT,
    release_date DATE,
    rating DOUBLE PRECISION DEFAULT NULL,
    FOREIGN KEY (season_id) REFERENCES season(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS tv_cast (
    media_id INT NOT NULL,
    cast_member_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (media_id) REFERENCES tv_show(id) ON DELETE CASCADE
);

