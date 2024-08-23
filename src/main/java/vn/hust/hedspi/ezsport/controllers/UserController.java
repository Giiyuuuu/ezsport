package vn.hust.hedspi.ezsport.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.hust.hedspi.ezsport.dtos.user.CreateUserRequest;
import vn.hust.hedspi.ezsport.dtos.user.UpdateUserRequest;
import vn.hust.hedspi.ezsport.entities.User;
import vn.hust.hedspi.ezsport.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("api/v1/user")
@NoArgsConstructor()
@AllArgsConstructor()
public class UserController {
    @Autowired
    private UserRepository userRepository;

    // Create
    @PostMapping()
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserRequest requestBody) {
        User user = new User();
        user.setEmail(requestBody.getEmail());
        user.setFirstname(requestBody.getFirstname());
        user.setLastname(requestBody.getLastname());
        user.setPassword(requestBody.getPassword());

        return ResponseEntity.ok(userRepository.save(user));
    }

    // List User
    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    // Show
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @Valid @RequestBody() UpdateUserRequest requestBody) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOptional.get();
        user.setEmail(requestBody.getEmail());
        user.setFirstname(requestBody.getFirstname());
        user.setLastname(requestBody.getLastname());
        user.setPassword(requestBody.getPassword());

        return ResponseEntity.ok(userRepository.save(user));
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}