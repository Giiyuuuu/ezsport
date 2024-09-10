package vn.hust.hedspi.ezsport.domain.mappers;

import org.mapstruct.Mapper;
import vn.hust.hedspi.ezsport.domain.dtos.playerbooking.CreatePlayerBookingRequest;
import vn.hust.hedspi.ezsport.domain.dtos.playerbooking.PlayerBookingResponse;
import vn.hust.hedspi.ezsport.database.entities.PlayerBooking;

@Mapper(componentModel = "spring")
public interface PlayerBookingMapper {
    PlayerBooking toCreatePlayerBookingRequest(CreatePlayerBookingRequest createPlayerBookingRequest);

    PlayerBookingResponse toPlayerBookingResponse(PlayerBooking playerBooking);
}
