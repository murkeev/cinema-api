package murkeev.cinemaApi.service;

import lombok.AllArgsConstructor;
import murkeev.cinemaApi.entity.Movie;
import murkeev.cinemaApi.repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<Movie> findAll() {
        List<Movie> movies = movieRepository.findAll();
        if (movies.isEmpty()) {
            throw new RuntimeException("Movies not found.");
        }
        return movies;
    }

    @Transactional
    public Movie addMovie(Movie movie) {
        try {
            return movieRepository.save(movie);
        } catch (Exception e) {
            throw new RuntimeException("Fail.");
        }
    }

    @Transactional
    public boolean removeMovie(String title) {
        Movie existingMovie = movieRepository.findMovieByTitle(title)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        try {
            movieRepository.delete(existingMovie);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Fail.");
        }
    }

    @Transactional
    public Movie update(Movie updateMovie) {
        Movie existingMovie = movieRepository.findMovieByTitle(updateMovie.getTitle())
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        modelMapper.map(updateMovie, existingMovie);
        try {
            return movieRepository.save(existingMovie);
        } catch (Exception e) {
            throw new RuntimeException("Fail.");
        }
    }
}
