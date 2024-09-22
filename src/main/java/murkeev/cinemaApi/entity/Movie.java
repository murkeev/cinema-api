package murkeev.cinemaApi.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private String genre;
}
