package vn.hust.hedspi.ezsport.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequest {

    private String firstname;


    private String lastname;

    @Email
    private String email;

    @Size(min = 6, max = 25)
    private String password;
}
