package vn.hust.hedspi.ezsport.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.hust.hedspi.ezsport.dtos.field.CreateFieldRequest;
import vn.hust.hedspi.ezsport.dtos.field.FieldResponse;
import vn.hust.hedspi.ezsport.entities.Field;

@Mapper(componentModel = "spring")
public interface FieldMapper {
    Field toCreateFieldRequest(CreateFieldRequest request);

    @Mapping(source = "location.x",target = "longitude")
    @Mapping(source = "location.y",target = "latitude")
    FieldResponse toFieldResponse(Field field);
}
