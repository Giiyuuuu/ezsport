package vn.hust.hedspi.ezsport.dtos.playerBooking;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdatePlayerBookingRequest {
    @NotNull
    String bookingPlayerId;

    @NotNull
    String bookedPlayerId;

    @NotNull
    String feedId;

    @NotNull
    @Pattern(regexp = "accepted|waiting|rejected")
    String status;
}
