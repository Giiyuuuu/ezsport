package vn.hust.hedspi.ezsport.mappers;

import org.mapstruct.Mapper;
import vn.hust.hedspi.ezsport.dtos.field.CreateFieldRequest;
import vn.hust.hedspi.ezsport.dtos.field.FieldResponse;
import vn.hust.hedspi.ezsport.entities.Field;

@Mapper(componentModel = "spring")
public interface FieldMapper {
    Field toCreateFieldRequest(CreateFieldRequest request);

    FieldResponse toFieldResponse(Field field);
}
