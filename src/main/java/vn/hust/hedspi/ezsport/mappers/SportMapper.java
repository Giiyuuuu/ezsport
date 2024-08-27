package vn.hust.hedspi.ezsport.mappers;

import org.mapstruct.Mapper;
import vn.hust.hedspi.ezsport.dtos.sport.CreateSportRequest;
import vn.hust.hedspi.ezsport.dtos.sport.SportResponse;
import vn.hust.hedspi.ezsport.entities.Sport;

@Mapper(componentModel = "spring")
public interface SportMapper {
    Sport toCreateSportRequest(CreateSportRequest request);

    SportResponse toSportResponse(Sport sport);
}
