package murkeev.cinemaApi.controller;

import lombok.AllArgsConstructor;
import murkeev.cinemaApi.entity.User;
import murkeev.cinemaApi.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @QueryMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @QueryMapping
    public User getByUsername(@Argument String username) {
        return userService.findByUsername(username);
    }

    @QueryMapping
    public User getByEmail(@Argument String email) {
        return userService.findByEmail(email);
    }

    @QueryMapping
    public List<User> getByFirstname(@Argument String firstname) {
        return userService.findByFirstname(firstname);
    }

    @QueryMapping
    public List<User> getByLastname(@Argument String lastname) {
        return userService.findByLastname(lastname);
    }

    @QueryMapping
    public List<User> getByCreatedDate(@Argument LocalDate createdDate) {
        return userService.findByDate(createdDate);
    }

    @MutationMapping
    public boolean removeUser(@Argument String username) {
        return userService.removeUser(username);
    }
}

