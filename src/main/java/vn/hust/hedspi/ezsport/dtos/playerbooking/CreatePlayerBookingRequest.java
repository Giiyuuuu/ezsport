package vn.hust.hedspi.ezsport.dtos.playerbooking;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreatePlayerBookingRequest {
    @NotNull
    String bookingPlayerId;

    @NotNull
    String bookedPlayerId;

    @NotNull
    String feedId;
}
