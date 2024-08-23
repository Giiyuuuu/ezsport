package vn.hust.hedspi.ezsport.dtos;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateFieldRequest {
    @Size(min = 4,message = "FIELD_NAME_INVALID")
    String name;
    double longitude;
    double latitude;
    String description;
}
