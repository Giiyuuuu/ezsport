package vn.hust.hedspi.ezsport.domain.dtos.fieldorder;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateFieldOrderRequest {
    @NotNull
    String fieldId;

    @NotNull
    LocalTime start;

    @NotNull
    LocalTime end;

    @NotNull
    @FutureOrPresent
    LocalDate date;

    @NotNull
    @DecimalMin("0.0")
    double price;

    @NotNull
    String userId;

    @NotNull
    @Future
    LocalDateTime paidAt;
}
