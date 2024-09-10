package vn.hust.hedspi.ezsport.domain.mappers;

import org.mapstruct.Mapper;
import vn.hust.hedspi.ezsport.domain.dtos.sport.CreateSportRequest;
import vn.hust.hedspi.ezsport.domain.dtos.sport.SportResponse;
import vn.hust.hedspi.ezsport.database.entities.Sport;

@Mapper(componentModel = "spring")
public interface SportMapper {
    Sport toCreateSportRequest(CreateSportRequest request);

    SportResponse toSportResponse(Sport sport);
}
