package vn.hust.hedspi.ezsport.domain.mappers;

import org.mapstruct.Mapper;
import vn.hust.hedspi.ezsport.domain.dtos.fieldorder.CreateFieldOrderRequest;
import vn.hust.hedspi.ezsport.domain.dtos.fieldorder.FieldOrderResponse;
import vn.hust.hedspi.ezsport.database.entities.FieldOrder;

@Mapper(componentModel = "spring")
public interface FieldOrderMapper {
    FieldOrder toCreateFieldOrderRequest(CreateFieldOrderRequest createFieldOrderRequest);

    FieldOrderResponse toFieldOrderResponse(FieldOrder fieldOrder);

}
