package vn.hust.hedspi.ezsport.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.hust.hedspi.ezsport.dtos.fieldorder.CreateFieldOrderRequest;
import vn.hust.hedspi.ezsport.dtos.fieldorder.FieldOrderResponse;
import vn.hust.hedspi.ezsport.entities.FieldOrder;

@Mapper(componentModel = "spring")
public interface FieldOrderMapper {
    FieldOrder toCreateFieldOrderRequest(CreateFieldOrderRequest createFieldOrderRequest);

    FieldOrderResponse toFieldOrderResponse(FieldOrder fieldOrder);

}
