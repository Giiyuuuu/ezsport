package vn.hust.hedspi.ezsport.dtos.user;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String id;
    private String email;
    private String firstname;
    private String lastname;
}
