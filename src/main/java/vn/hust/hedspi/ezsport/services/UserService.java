package vn.hust.hedspi.ezsport.services;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.hust.hedspi.ezsport.data.UserData;
import vn.hust.hedspi.ezsport.dtos.ApiResponse;
import vn.hust.hedspi.ezsport.dtos.user.CreateUserRequest;
import vn.hust.hedspi.ezsport.dtos.user.CreateUserResponse;
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

    public ApiResponse<CreateUserResponse> createUser(CreateUserRequest request){
        User user = userMapper.toCreateUserRequest(request);
        CreateUserResponse createUserResponse = userMapper.toCreateUserResponse(userRepository.save(user));
        ApiResponse response = new ApiResponse();
        response.setMessage("Create user successful !");
        response.setResult(createUserResponse);

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
