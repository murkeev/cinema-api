package murkeev.cinemaApi.entity;

import jakarta.validation.constraints.Email;

import lombok.*;
import murkeev.cinemaApi.enums.Role;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.*;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    private String password;

    @Email
    @Indexed(unique = true)
    private String email;

    private String firstname;

    private String lastname;

    private Role role = Role.ROLE_USER;

    @CreatedDate
    private LocalDateTime createdDate;
}
