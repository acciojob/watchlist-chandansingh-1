package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepo;

    public void addMovie(Movie movie) {
        movieRepo.addMovieToDB(movie);
    }

    public void addDirector(Director director) {
        movieRepo.addDirectorToDB(director);
    }

    public boolean addDirectorAndTheirMovie(String movieName, String directorName) {

        return movieRepo.addDirectorAndMoviePairToDB(directorName, movieName);
    }

    public Movie getMovie(String movieName) {
        return movieRepo.getMovieFromDB(movieName);
    }

    public Director getDirector(String directorName) {
        return movieRepo.getDirectorFromDB(directorName);
    }

    public List<String> getAllMoviesOFDirector(String directorName) {
        return movieRepo.getAllMoviesDoneByDirector(directorName);
    }

    public List<String> getAllMovies() {
        return movieRepo.allMoviesFromDB();
    }

    public boolean deleteDirectorMovies(String directorName) {
        return movieRepo.deleteDirectorAndTheirMovies(directorName);
    }

    public void deleteAllDirectorsAndMovies() {
        movieRepo.deleteAllDirectorsAndTheirMovies();
    }
}
