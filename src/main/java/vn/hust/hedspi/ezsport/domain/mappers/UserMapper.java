package vn.hust.hedspi.ezsport.domain.mappers;

import org.mapstruct.Mapper;
import vn.hust.hedspi.ezsport.domain.dtos.user.CreateUserRequest;
import vn.hust.hedspi.ezsport.domain.dtos.user.UserResponse;
import vn.hust.hedspi.ezsport.database.entities.User;


@Mapper(componentModel = "spring")
public interface UserMapper {
    User toCreateUserRequest(CreateUserRequest request);

    UserResponse toUserResponse(User user);
}
