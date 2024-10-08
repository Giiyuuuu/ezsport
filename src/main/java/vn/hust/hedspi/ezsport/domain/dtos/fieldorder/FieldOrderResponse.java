package vn.hust.hedspi.ezsport.domain.dtos.fieldorder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class FieldOrderResponse {
    String id;
    LocalTime start;
    LocalTime end;
    LocalDate date;
    double price;
    String userId;
    LocalDateTime paidAt;
}
