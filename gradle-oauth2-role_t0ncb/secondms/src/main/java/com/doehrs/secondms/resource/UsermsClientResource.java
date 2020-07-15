package com.doehrs.secondms.resource;

import com.doehrs.secondms.model.User;
import com.doehrs.secondms.service.UsermsClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/usersms/client")
public class UsermsClientResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsermsClientResource.class);

    private final UsermsClientService usermsClientService;

    public UsermsClientResource(UsermsClientService usermsClientService) {
        this.usermsClientService = usermsClientService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        LOGGER.info("Getting all users");
        System.out.println("UsermsClientResource: Getting all users");
        List<User> allUsers = usermsClientService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getSingleUser(@PathVariable(name = "userId") Long userId) {
        LOGGER.info("Getting single users");
        Optional<User> user = usermsClientService.getSingleUser(userId);

        if (user.isPresent()) {
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody User user) throws URISyntaxException {
        Long userId = usermsClientService.createUser(user);
        return ResponseEntity.created(new URI("/" + userId)).build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "userId") Long userId) {
        LOGGER.info("Getting all users");
        usermsClientService.deleteUser(userId);

        return ResponseEntity.accepted().build();
    }
}
