package murkeev.cinemaApi.entity;

import lombok.*;
import murkeev.cinemaApi.enums.Genre;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Set;

@Document(collection = "movies")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie {
    @Id
    private String id;

    @Indexed(unique = true, name = "title")
    private String title;

    private int duration;

    private String description;

    private String releaseDate;

    private String ageRating;

    private Set<Genre> genres;
}
