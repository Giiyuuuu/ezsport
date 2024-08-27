package vn.hust.hedspi.ezsport.mappers;

import org.mapstruct.Mapper;
import vn.hust.hedspi.ezsport.dtos.playerBooking.CreatePlayerBookingRequest;
import vn.hust.hedspi.ezsport.dtos.playerBooking.PlayerBookingResponse;
import vn.hust.hedspi.ezsport.entities.PlayerBooking;

@Mapper(componentModel = "spring")
public interface PlayerBookingMapper {
    PlayerBooking toCreatePlayerBookingRequest(CreatePlayerBookingRequest createPlayerBookingRequest);

    PlayerBookingResponse toPlayerBookingResponse(PlayerBooking playerBooking);
}
