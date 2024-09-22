package murkeev.cinemaApi.repository;

import murkeev.cinemaApi.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    List<User> findUsersByFirstname(String firstname);

    List<User> findUsersByLastname(String lastname);

    @Query("{ 'createdDate' : { $gte: ?0, $lt: ?1 } }")
    List<User> findUsersByCreatedDate(LocalDate startDate, LocalDate endDate);
}
