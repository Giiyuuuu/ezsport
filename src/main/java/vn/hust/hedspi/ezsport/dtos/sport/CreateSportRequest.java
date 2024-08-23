package vn.hust.hedspi.ezsport.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateSportRequest {
    @NotBlank(message = "Sport can't not blank")
    String name;
}
