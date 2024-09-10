package vn.hust.hedspi.ezsport.domain.dtos.field;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateFieldRequest {
    @NotBlank
    String name;

    @NotNull
    @DecimalMin(value = "-180.0")
    @DecimalMax(value = "180.0")
    double longitude;

    @NotNull
    @DecimalMin(value = "-90.0")
    @DecimalMax(value = "90.0")
    double latitude;

    @NotBlank
    @Size(max = 1000)
    String description;

    @NotNull
    Integer sportId;

    @NotBlank
    String ownerId;

    String avatar;

    @NotBlank
    @Pattern(regexp = "waiting|active|inactive")
    String isActive;

    String status;
}
