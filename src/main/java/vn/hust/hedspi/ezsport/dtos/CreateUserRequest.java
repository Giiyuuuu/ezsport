package vn.hust.hedspi.ezsport.dtos;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String firstname;

    private String lastname;

    private String email;

    private String password;
}
