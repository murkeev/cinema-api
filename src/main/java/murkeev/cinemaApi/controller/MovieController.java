package murkeev.cinemaApi.controller;

import lombok.AllArgsConstructor;
import murkeev.cinemaApi.dto.MovieInput;
import murkeev.cinemaApi.dto.UpdateMovieDto;
import murkeev.cinemaApi.entity.Movie;
import murkeev.cinemaApi.enums.Genre;
import murkeev.cinemaApi.service.MovieService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @QueryMapping
    public List<Movie> getAllMovies() {
        List<Movie> movies = movieService.findAll();
        if (movies.isEmpty()) {
            throw new RuntimeException("No movies found");
        }
        return movies;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @QueryMapping
    public Movie getMovieByTitle(@Argument String title) {
        return movieService.findByTitle(title);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @QueryMapping
    public List<Movie> getByGenre(@Argument Genre genre) {
        return movieService.findByGenre(genre);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @MutationMapping
    public boolean removeMovie(@Argument String title) {
        return movieService.removeMovie(title);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @MutationMapping
    public Movie addMovie(@Argument MovieInput movieInput) {
        return movieService.addMovie(movieInput);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @MutationMapping
    public Movie updateMovie(@Argument UpdateMovieDto updateMovie) {
        return movieService.update(updateMovie);
    }
}
