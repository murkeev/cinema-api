package murkeev.cinemaApi.repository;

import murkeev.cinemaApi.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
    Optional<Movie> findMovieByTitle(String title);
}
