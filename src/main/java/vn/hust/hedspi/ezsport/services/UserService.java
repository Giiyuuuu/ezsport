package vn.hust.hedspi.ezsport.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.hust.hedspi.ezsport.domain.dtos.ApiResponse;
import vn.hust.hedspi.ezsport.domain.dtos.user.CreateUserRequest;
import vn.hust.hedspi.ezsport.domain.dtos.user.UserResponse;
import vn.hust.hedspi.ezsport.domain.dtos.user.UpdateUserRequest;
import vn.hust.hedspi.ezsport.database.entities.User;
import vn.hust.hedspi.ezsport.domain.dtos.user.UserSearchingResponseProjection;
import vn.hust.hedspi.ezsport.domain.mappers.UserMapper;
import vn.hust.hedspi.ezsport.database.repositories.UserRepository;
import vn.hust.hedspi.ezsport.services.searching.searchinginterface.ISearchingUser;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    ISearchingUser searching;

    public ApiResponse<Page<UserResponse>> listUser(Pageable pageable){
        ApiResponse<Page<UserResponse>> response = new ApiResponse<>();
        Page<User> userPage = userRepository.findAll(pageable);
        Page<UserResponse> userResponsePage = userPage.map(userMapper::toUserResponse);
        response.setMessage("Get list user successful !");
        response.setResult(userResponsePage);

        return response;
    }

    public ApiResponse<UserResponse> createUser(CreateUserRequest request){
        User user = userMapper.toCreateUserRequest(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

//        Set<String> roles = new HashSet<>();
//        roles.add(Role.USER.name());

//        user.setRoles(roles);
        UserResponse userResponse = userMapper.toUserResponse(userRepository.save(user));
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setMessage("Create user successful !");
        response.setResult(userResponse);

        return response;
    }

    public ApiResponse<UserResponse> getUserById(String id){
        User user = userRepository.findById(id).orElse(null);
        UserResponse userResponse = userMapper.toUserResponse(user);
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setMessage("Get user successful !");
        response.setResult(userResponse);

        return response;
    }

    public ApiResponse<UserResponse> updateUser(String id, UpdateUserRequest request){
        User user = userRepository.findById(id).orElse(null);
        ApiResponse<UserResponse> response = new ApiResponse<>();
        if(user == null){
            response.setMessage("User not found !");
            return response;
        }
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        UserResponse userResponse = userMapper.toUserResponse(userRepository.save(user));
        response.setMessage("Update user successful !");
        response.setResult(userResponse);

        return response;
    }

    public ApiResponse<Void> deleteUser(String id){
        userRepository.deleteById(id);
        ApiResponse<Void> response = new ApiResponse<>();
        response.setMessage("Delete user successful !");
        return response;
    }

    public ApiResponse<List<UserSearchingResponseProjection>> searchUser(String username, Double longitude,Double latitude, double radius){
        Point location;
        GeometryFactory geometryFactory = new GeometryFactory();

        if(longitude == null || latitude == null){
            location = geometryFactory.createPoint(new Coordinate(0.0,0.0));
        }else{
            location = geometryFactory.createPoint(new Coordinate(longitude,latitude));
        }


        return ApiResponse.<List<UserSearchingResponseProjection>>builder()
                .result(searching.searchUser(username,location,radius))
                .build();
    }
}
