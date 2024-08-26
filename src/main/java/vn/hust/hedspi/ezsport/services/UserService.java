package vn.hust.hedspi.ezsport.services;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.hust.hedspi.ezsport.data.UserData;
import vn.hust.hedspi.ezsport.dtos.ApiResponse;
import vn.hust.hedspi.ezsport.dtos.user.CreateUserRequest;
import vn.hust.hedspi.ezsport.dtos.user.UserResponse;
import vn.hust.hedspi.ezsport.dtos.user.UpdateUserRequest;
import vn.hust.hedspi.ezsport.entities.User;
import vn.hust.hedspi.ezsport.mappers.UserMapper;
import vn.hust.hedspi.ezsport.repositories.UserRepository;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public ApiResponse<Page<UserResponse>> listUser(Pageable pageable){
        ApiResponse response = new ApiResponse();
        Page<User> userPage = userRepository.findAll(pageable);
        Page<UserResponse> userResponsePage = userPage.map(userMapper::toUserResponse);
        response.setMessage("Get list user successful !");
        response.setResult(userResponsePage);

        return response;
    }

    public ApiResponse<UserResponse> createUser(CreateUserRequest request){
        User user = userMapper.toCreateUserRequest(request);
        UserResponse userResponse = userMapper.toUserResponse(userRepository.save(user));
        ApiResponse response = new ApiResponse();
        response.setMessage("Create user successful !");
        response.setResult(userResponse);

        return response;
    }

    public ApiResponse<UserResponse> getUserById(String id){
        User user = userRepository.findById(id).orElse(null);
        UserResponse userResponse = userMapper.toUserResponse(user);
        ApiResponse response = new ApiResponse();
        response.setMessage("Get user successful !");
        response.setResult(userResponse);

        return response;
    }

    public ApiResponse<UserResponse> updateUser(String id, UpdateUserRequest request){
        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            return null;
        }
        user = userMapper.toUpdateUserRequest(request);
        UserResponse createUserResponse = userMapper.toUserResponse(userRepository.save(user));
        ApiResponse response = new ApiResponse();
        response.setMessage("Update user successful !");
        response.setResult(createUserResponse);

        return response;
    }

    public ApiResponse<Void> deleteUser(String id){
        userRepository.deleteById(id);
        ApiResponse response = new ApiResponse();
        response.setMessage("Delete user successful !");
        return response;
    }

    @PostConstruct
    public void init(){
        long count = userRepository.count();
        if(count < 10){
            UserData data = new UserData();
            userRepository.saveAll(data.generate(1000000));
        }
    }
}
