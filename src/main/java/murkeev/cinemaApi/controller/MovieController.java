package murkeev.cinemaApi.controller;

import lombok.AllArgsConstructor;
import murkeev.cinemaApi.entity.Movie;
import murkeev.cinemaApi.service.MovieService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class MovieController {
    private final MovieService movieService;


    @QueryMapping
    public List<Movie> getAllMovies() {
        return movieService.findAll();
    }

    @MutationMapping
    public boolean removeMovie(@Argument String title) {
        return movieService.removeMovie(title);
    }
}
