package vn.hust.hedspi.ezsport.mappers;

import org.mapstruct.Mapper;
import vn.hust.hedspi.ezsport.dtos.user.CreateUserRequest;
import vn.hust.hedspi.ezsport.dtos.user.UserResponse;
import vn.hust.hedspi.ezsport.entities.User;


@Mapper(componentModel = "spring")
public interface UserMapper {
    User toCreateUserRequest(CreateUserRequest request);

    UserResponse toUserResponse(User user);
}
