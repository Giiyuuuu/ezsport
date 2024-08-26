package vn.hust.hedspi.ezsport.mappers;

import org.mapstruct.Mapper;
import vn.hust.hedspi.ezsport.dtos.user.CreateUserRequest;
import vn.hust.hedspi.ezsport.dtos.user.UserResponse;
import vn.hust.hedspi.ezsport.dtos.user.UpdateUserRequest;
import vn.hust.hedspi.ezsport.entities.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toCreateUserRequest(CreateUserRequest request);

    User toUpdateUserRequest(UpdateUserRequest request);

    UserResponse toUserResponse(User user);
}
