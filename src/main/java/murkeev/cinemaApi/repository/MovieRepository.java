package murkeev.cinemaApi.repository;

import murkeev.cinemaApi.entity.Movie;
import murkeev.cinemaApi.enums.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
    Optional<Movie> findMovieByTitle(String title);

    @Query("{ 'genres': { $in: [?0] } }")
    List<Movie> findMoviesByGenre(Genre genre);
}
