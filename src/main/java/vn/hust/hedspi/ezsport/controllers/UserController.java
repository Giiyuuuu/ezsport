package vn.hust.hedspi.ezsport.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import vn.hust.hedspi.ezsport.dtos.ApiResponse;
import vn.hust.hedspi.ezsport.dtos.user.CreateUserRequest;
import vn.hust.hedspi.ezsport.dtos.user.UserResponse;
import vn.hust.hedspi.ezsport.dtos.user.UpdateUserRequest;
import vn.hust.hedspi.ezsport.repositories.UserRepository;
import vn.hust.hedspi.ezsport.services.UserService;

@RestController()
@RequestMapping("api/v1/user")
@NoArgsConstructor()
@AllArgsConstructor()
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    // Create
    @PostMapping()
    public ApiResponse<UserResponse> createUser(@Valid @RequestBody CreateUserRequest requestBody) {
        return userService.createUser(requestBody);
    }

    // List User
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ApiResponse<Page<UserResponse>> listUser(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1000") int size
    ) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(SecurityContextHolder.getContext().toString());
        log.info(authentication.toString());
        log.info("Id: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
        Pageable pageable = PageRequest.of(page, size);

        return userService.listUser(pageable);
    }

    // Show
    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    //Update
    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable String id, @Valid @RequestBody() UpdateUserRequest requestBody) {
        return userService.updateUser(id, requestBody);
    }

    //Delete
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }
}