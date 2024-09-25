package murkeev.cinemaApi.service;

import lombok.AllArgsConstructor;
import murkeev.cinemaApi.dto.RegistrationUserDto;
import murkeev.cinemaApi.entity.User;
import murkeev.cinemaApi.jwt.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsServiceImpl userDetailsService;

    public String authenticateAndGenerateToken(String login, String password) {
        userService.login(login);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        UserDetails userDetails = userDetailsService.loadUserByUsername(login);
        return jwtTokenUtil.generateToken(userDetails);
    }

    public User save(RegistrationUserDto registrationUserDto) {
        return userService.createUser(registrationUserDto);
    }
}
