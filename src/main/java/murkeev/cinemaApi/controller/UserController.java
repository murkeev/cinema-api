package murkeev.cinemaApi.controller;

import lombok.AllArgsConstructor;
import murkeev.cinemaApi.dto.UpdateUser;
import murkeev.cinemaApi.entity.User;
import murkeev.cinemaApi.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @QueryMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @QueryMapping
    public User getByUsername(@Argument String username) {
        return userService.login(username);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @QueryMapping
    public User getByEmail(@Argument String email) {
        return userService.login(email);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @QueryMapping
    public List<User> getByFirstname(@Argument String firstname) {
        return userService.findByFirstname(firstname);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @QueryMapping
    public List<User> getByLastname(@Argument String lastname) {
        return userService.findByLastname(lastname);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @QueryMapping
    public List<User> getByCreatedDate(@Argument LocalDate createdDate) {
        return userService.findByDate(createdDate);
    }

    @PreAuthorize("hasRole('USER')")
    @QueryMapping
    public User profile() {
        return userService.profile();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @MutationMapping
    public boolean removeUser(@Argument String username) {
        return userService.removeUser(username);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @MutationMapping
    public User update(@Argument UpdateUser updateUser) {
        return userService.update(updateUser);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @MutationMapping
    public boolean changePassword(@Argument String oldPassword, @Argument String newPassword) {
        return userService.changePassword(oldPassword, newPassword);
    }

    @PreAuthorize("hasRole('USER')")
    @MutationMapping
    public boolean deleteAccount() {
        return userService.deleteAccount();
    }
}

