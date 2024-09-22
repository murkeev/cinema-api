package murkeev.cinemaApi.service;

import lombok.AllArgsConstructor;
import murkeev.cinemaApi.dto.RegistrationUserDto;
import murkeev.cinemaApi.entity.User;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserService userService;

    public User save(RegistrationUserDto registrationUserDto) {
        return userService.createUser(registrationUserDto);
    }
}
