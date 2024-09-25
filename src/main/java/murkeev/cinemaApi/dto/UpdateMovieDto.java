package murkeev.cinemaApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import murkeev.cinemaApi.enums.Genre;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateMovieDto {
    private String title;
    private int duration;
    private String description;
    private LocalDate releaseDate;
    private String ageRating;
    private Set<Genre> genres;
}
