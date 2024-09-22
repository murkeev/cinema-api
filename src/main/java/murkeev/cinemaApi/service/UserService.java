package murkeev.cinemaApi.service;

import lombok.AllArgsConstructor;
import murkeev.cinemaApi.dto.RegistrationUserDto;
import murkeev.cinemaApi.entity.User;
import murkeev.cinemaApi.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new RuntimeException("Users not found.");
        }
        return users;
    }

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found."));
    }


    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found."));
    }

    @Transactional(readOnly = true)
    public List<User> findByFirstname(String firstname) {
        return userRepository.findUsersByFirstname(firstname);
    }

    @Transactional(readOnly = true)
    public List<User> findByLastname(String lastname) {
        return userRepository.findUsersByLastname(lastname);
    }

    @Transactional(readOnly = true)
    public List<User> findByDate(LocalDate date) {
        LocalDate startDate = date.atStartOfDay().toLocalDate();
        LocalDate endDate = date.plusDays(1);
        return userRepository.findUsersByCreatedDate(startDate, endDate);
    }

    @Transactional
    public User createUser(RegistrationUserDto registrationUserDto) {
        User user = modelMapper.map(registrationUserDto, User.class);
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
    public User update(User updateUser) {
        User existingUser = userRepository.findByUsername(updateUser.getUsername())
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        modelMapper.map(updateUser, existingUser);
        try {
            return userRepository.save(existingUser);
        } catch (Exception e) {
            throw new RuntimeException("Fail.");
        }
    }
}
