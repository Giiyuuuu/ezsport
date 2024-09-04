package vn.hust.hedspi.ezsport.dtos.feed;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateFeedRequest {
    @NotNull
    String userId;

    @Size()
    String description;

    @NotNull
    LocalTime start;

    @NotNull
    LocalTime end;

    @NotNull
    @FutureOrPresent
    LocalDate date;

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
