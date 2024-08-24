package vn.hust.hedspi.ezsport.mappers;

import org.mapstruct.Mapper;
import vn.hust.hedspi.ezsport.dtos.user.CreateUserRequest;
import vn.hust.hedspi.ezsport.dtos.user.CreateUserResponse;
import vn.hust.hedspi.ezsport.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toCreateUserRequest(CreateUserRequest request);

    CreateUserResponse toCreateUserResponse(User user);
}
