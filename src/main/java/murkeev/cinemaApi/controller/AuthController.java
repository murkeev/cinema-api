package murkeev.cinemaApi.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import murkeev.cinemaApi.dto.RegistrationUserDto;
import murkeev.cinemaApi.entity.User;
import murkeev.cinemaApi.service.AuthService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @MutationMapping
    public User addUser( @Valid @Argument RegistrationUserDto registrationUserDto) {
        return authService.save(registrationUserDto);
    }
}
