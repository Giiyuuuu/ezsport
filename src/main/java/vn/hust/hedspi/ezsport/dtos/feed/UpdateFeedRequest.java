package vn.hust.hedspi.ezsport.dtos.feed;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateFeedRequest {
    @NotNull
    String userId;

    @Size
    String description;

    @NotNull
    LocalTime start;

    @NotNull
    LocalTime end;

    @NotNull
    @FutureOrPresent
    LocalDate date;

    @NotNull
    @Pattern(regexp = "looking|closed")
    String status;

    @NotNull
    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    double latitude;

    @NotNull
    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    double longitude;

    @NotNull
    Integer sportId;
}
