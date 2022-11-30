package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/movies/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {

        movieService.addMovie(movie);
        return new ResponseEntity("Movie Add SuccessFully", HttpStatus.CREATED);
    }

    @PostMapping("/movies/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director) {
        movieService.addDirector(director);
        return new ResponseEntity("Movie Add SuccessFully", HttpStatus.CREATED);
    }

    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("name") String movieName, @RequestParam("dirName") String directorName) {

        if(movieService.addDirectorAndTheirMovie(movieName, directorName))
            return new ResponseEntity("Added Successfully", HttpStatus.CREATED);

        return new ResponseEntity("Director Not Found", HttpStatus.CREATED);
    }

    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String movieName) {

        return new ResponseEntity(movieService.getMovie(movieName), HttpStatus.ACCEPTED);
    }

    @GetMapping(" /movies/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String directorName) {

        return new ResponseEntity(movieService.getDirector(directorName), HttpStatus.ACCEPTED);
    }

    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("director") String directorName) {

        return new ResponseEntity(movieService.getAllMoviesOFDirector(directorName), HttpStatus.ACCEPTED);
    }

    @GetMapping("/movies/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies() {

        return new ResponseEntity(movieService.getAllMovies(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("name") String directorName) {
        if(movieService.deleteDirectorMovies(directorName))
            return new ResponseEntity("deleted Successful", HttpStatus.ACCEPTED);

        return new ResponseEntity("Not Found As Director "+ directorName, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<String> deleteAllDirectors() {
        movieService.deleteAllDirectorsAndMovies();
        return new ResponseEntity("deleted", HttpStatus.ACCEPTED);
    }
}
