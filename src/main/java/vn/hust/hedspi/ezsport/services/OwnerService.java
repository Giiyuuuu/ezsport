package vn.hust.hedspi.ezsport.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import vn.hust.hedspi.ezsport.domain.dtos.field.CreateFieldRequest;
import vn.hust.hedspi.ezsport.domain.dtos.field.FieldResponse;
import vn.hust.hedspi.ezsport.domain.dtos.fieldorder.CreateListFieldOrderRequest;
import vn.hust.hedspi.ezsport.domain.dtos.fieldorder.FieldOrderResponse;
import vn.hust.hedspi.ezsport.database.entities.Field;
import vn.hust.hedspi.ezsport.database.entities.FieldOrder;
import vn.hust.hedspi.ezsport.database.entities.Sport;
import vn.hust.hedspi.ezsport.database.entities.User;
import vn.hust.hedspi.ezsport.application.exceptions.AppException;
import vn.hust.hedspi.ezsport.application.exceptions.ErrorCode;
import vn.hust.hedspi.ezsport.domain.mappers.FieldMapper;
import vn.hust.hedspi.ezsport.domain.mappers.FieldOrderMapper;
import vn.hust.hedspi.ezsport.database.repositories.FieldOrderRepository;
import vn.hust.hedspi.ezsport.database.repositories.FieldRepository;
import vn.hust.hedspi.ezsport.database.repositories.SportRepository;
import vn.hust.hedspi.ezsport.database.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class OwnerService {
    UserRepository userRepository;
    FieldRepository fieldRepository;
    SportRepository sportRepository;
    FieldMapper fieldMapper;
    FieldOrderMapper fieldOrderMapper;
    FieldOrderRepository fieldOrderRepository;

    public List<FieldResponse> getOwnField(String userId){
        List<Field> fieldList =  fieldRepository.getFieldsByOwnerId(userId);
        List<FieldResponse> fieldResponseList = new ArrayList<>();
        fieldList.forEach(field -> {
            fieldResponseList.add(fieldMapper.toFieldResponse(field));
        });
        return fieldResponseList;
    }

    public FieldResponse createField(CreateFieldRequest request,String userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_FOUND)
        );
        Sport sport = sportRepository.findById(request.getSportId()).orElseThrow(
                ()-> new AppException(ErrorCode.FIELD_NOT_FOUND)
        );

        Field field = fieldMapper.toCreateFieldRequest(request);
        field.setOwner(user);
        field.setSport(sport);

        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(new Coordinate(request.getLongitude(), request.getLatitude()));
        field.setLocation(point);

        return fieldMapper.toFieldResponse(fieldRepository.save(field));
    }

    public List<FieldOrderResponse> createListFieldOrder(CreateListFieldOrderRequest request, String userId) {
        User owner = userRepository.findById(userId).orElseThrow(
                ()-> new AppException(ErrorCode.USER_NOT_FOUND)
        );
        Field field = fieldRepository.findById(request.getFieldId()).orElseThrow(
                ()->new AppException(ErrorCode.FIELD_NOT_FOUND)
        );

        if(!field.getOwner().equals(owner)){
            throw new AppException(ErrorCode.AUTHORIZATION_DENIED);
        }

        List<FieldOrder> fieldOrderList = request.getListFieldOrder().stream().map(fieldOrderRequest-> {
            FieldOrder fieldOrder = fieldOrderMapper.toCreateFieldOrderRequest(fieldOrderRequest);
            fieldOrder.setField(field);
            return fieldOrder;
        }).toList();
        fieldOrderRepository.saveAll(fieldOrderList);

        return fieldOrderList.stream().map(fieldOrderMapper::toFieldOrderResponse).toList();
    }
}
