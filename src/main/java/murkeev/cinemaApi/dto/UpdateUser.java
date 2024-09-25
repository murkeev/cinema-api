package murkeev.cinemaApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUser {
    private String username;
    private String email;
    private String firstname;
    private String lastname;
}
