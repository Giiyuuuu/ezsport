package vn.hust.hedspi.ezsport.mappers;

import org.mapstruct.Mapper;
import vn.hust.hedspi.ezsport.dtos.fieldOrder.CreateFieldOrderRequest;
import vn.hust.hedspi.ezsport.dtos.fieldOrder.FieldOrderResponse;
import vn.hust.hedspi.ezsport.entities.FieldOrder;

@Mapper(componentModel = "spring")
public interface FieldOrderMapper {
    FieldOrder toCreateFieldOrderRequest(CreateFieldOrderRequest createFieldOrderRequest);

    FieldOrderResponse toFieldOrderResponse(FieldOrder fieldOrder);
}
