package vn.hust.hedspi.ezsport.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.hust.hedspi.ezsport.dtos.CreateUserRequest;
import vn.hust.hedspi.ezsport.entities.User;
import vn.hust.hedspi.ezsport.repositories.UserRepository;

@RestController()
@RequestMapping("api/v1/user")
@NoArgsConstructor()
@AllArgsConstructor()
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping()
    public Object createUser(@RequestBody()CreateUserRequest requestBody){
        User user = new User();
        user.setEmail(requestBody.getEmail());
        user.setFirstname(requestBody.getFirstname());
        user.setLastname(requestBody.getLastname());
        user.setPassword(requestBody.getPassword());

        return userRepository.save(user);
    }

    @GetMapping()
    public String hello(){
        return "HELLO WORLD";
    }

}
