package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Component
public class MovieRepository {

    Map<String, Movie> movies = new HashMap<>();
    Map<String, Director> directors = new HashMap<>();
    Map<String, List<String>> directorsWithMovies = new HashMap<>();

    public void addMovieToDB(Movie movie) {
        movies.put(movie.getName(), movie);
    }

    public Movie getMovieFromDB(String movieName) {

        if(movies.containsKey(movieName))
            return movies.get(movieName);

        return null;
    }

    public void addDirectorToDB(Director director) {
        directors.put(director.getName(), director);
    }

    public Director getDirectorFromDB(String dirName) {
        if(directors.containsKey(dirName))
            return directors.get(dirName);

        return null;
    }

    public boolean addDirectorAndMoviePairToDB(String directorName, String movieName) {

        if(directors.containsKey(directorName)) {
            if(!directorsWithMovies.containsKey(directorName))
                directorsWithMovies.put(directorName, new ArrayList<String>());

            if(movies.containsKey(movieName))
                directorsWithMovies.get(directorName).add(movieName);
            return true;
        }

        return false;
    }
    public List<String> getAllMoviesDoneByDirector(String directorName) {
        if(directorsWithMovies.containsKey(directorName))
            return directorsWithMovies.get(directorName);

        return null;
    }

    public List<String> allMoviesFromDB() {
        List<String> listOfMoviesName = new ArrayList<>();
        for(String movieName : movies.keySet())
            listOfMoviesName.add(movieName);

        return listOfMoviesName;
    }

    public boolean deleteDirectorAndTheirMovies(String directorName) {

        if(directorsWithMovies.containsKey(directorName)) {
            for (String movieName : directorsWithMovies.get(directorName)) {
                if (movies.containsKey(movieName))
                    movies.remove(movieName);
            }

            directorsWithMovies.remove(directorName);
        }

        if(directors.containsKey(directorName)) {
            directors.remove(directorName);
            return true;
        }
        return false;
    }

    public void deleteAllDirectorsAndTheirMovies() {

        for(String directorName : directors.keySet()) {
            if(directorsWithMovies.containsKey(directorName)) {
                for (String movieName : directorsWithMovies.get(directorName)) {
                    if (movies.containsKey(movieName))
                        movies.remove(movieName);
                }
                directorsWithMovies.remove(directorName);
            }

            directors.remove(directorName);
        }
    }


}
