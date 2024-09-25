package murkeev.cinemaApi.service;

import lombok.AllArgsConstructor;
import murkeev.cinemaApi.dto.RegistrationUserDto;
import murkeev.cinemaApi.dto.UpdateUser;
import murkeev.cinemaApi.entity.User;
import murkeev.cinemaApi.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new IllegalArgumentException("No authenticated user found");
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername()).orElseThrow(
                () -> new RuntimeException("User not found"));
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new RuntimeException("Users not found.");
        }
        return users;
    }

    @Transactional(readOnly = true)
    public User login(String login) {
        User user;
        if (login.contains("@")) {
            user = userRepository.findByEmail(login).orElseThrow(
                    () -> new RuntimeException("User with email " + login + " not found!"));
        } else {
            user = userRepository.findByUsername(login).orElseThrow(
                    () -> new RuntimeException("User with email " + login + " not found!"));
        }

        if (user == null) {
            throw new RuntimeException("User is null.");
        }
        return user;
    }

    @Transactional(readOnly = true)
    public User profile() {
        return getCurrentUser();
    }

    @Transactional(readOnly = true)
    public List<User> findByFirstname(String firstname) {
        List<User> users = userRepository.findUsersByFirstname(firstname);
        if (users.isEmpty()) {
            throw new RuntimeException("Users not found.");
        }
        return users;
    }

    @Transactional(readOnly = true)
    public List<User> findByLastname(String lastname) {
        List<User> users = userRepository.findUsersByLastname(lastname);
        if (users.isEmpty()) {
            throw new RuntimeException("Users not found.");
        }
        return users;
    }

    @Transactional(readOnly = true)
    public List<User> findByDate(LocalDate date) {
        LocalDate startDate = date.atStartOfDay().toLocalDate();
        LocalDate endDate = date.plusDays(1);
        List<User> users = userRepository.findUsersByCreatedDate(startDate, endDate);
        if (users.isEmpty()) {
            throw new RuntimeException("Users not found.");
        }
        return users;
    }

    @Transactional
    public User createUser(RegistrationUserDto registrationUserDto) {
        User user = modelMapper.map(registrationUserDto, User.class);
        user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new IllegalArgumentException("illegal");
        }
    }

    @Transactional
    public boolean removeUser(String username) {
        User existingUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        try {
            userRepository.delete(existingUser);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Fail.");
        }
    }

    @Transactional
    public User update(UpdateUser updateUser) {
        User existingUser = getCurrentUser();
        modelMapper.map(updateUser, existingUser);
        try {
            return userRepository.save(existingUser);
        } catch (Exception e) {
            throw new RuntimeException("Fail.");
        }
    }

    @Transactional
    public boolean changePassword(String oldPassword, String newPassword) {
        String passwordPattern =  "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z0-9]{8,}$";
        if(!newPassword.matches(passwordPattern)) {
            throw new RuntimeException("Password must be at least 8 characters long " +
                                       "and contain at least one uppercase letter, one lowercase letter, and one digit");
        }

        User existingUser = getCurrentUser();
        if (!passwordEncoder.matches(newPassword, existingUser.getPassword()) && passwordEncoder.matches(oldPassword, existingUser.getPassword())) {
            existingUser.setPassword(passwordEncoder.encode(newPassword));
        } else {
            throw new RuntimeException("The new password matches the old password or the old password is incorrect");
        }
        try {
            userRepository.save(existingUser);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Fail.");
        }
    }

    @Transactional
    public boolean deleteAccount() {
        try {
            userRepository.delete(getCurrentUser());
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Fail.");
        }
    }
}
