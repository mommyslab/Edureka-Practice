package com.doehrs.firstms.resource;

import com.doehrs.firstms.model.User;
import com.doehrs.firstms.service.UserService;
import com.doehrs.firstms.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/users")
public class UserResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        LOGGER.info("Getting all users");
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getSingleUser(@PathVariable(name = "userId") @Min(value = 1L, message = "User ID can not be less than one") Long userId) {
        Optional<User> user = userService.getSingleUser(userId);
        if(!user.isPresent()) {
            ResponseEntity.notFound();
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody(required = true) User user) {
        Long userId = userService.createUser(user);
        return ResponseEntity.created(URI.create("/api/users/" + userId)).build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "userId") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
