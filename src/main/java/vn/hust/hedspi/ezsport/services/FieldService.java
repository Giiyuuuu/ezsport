package vn.hust.hedspi.ezsport.services;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.hust.hedspi.ezsport.data.FieldData;
import vn.hust.hedspi.ezsport.dtos.ApiResponse;
import vn.hust.hedspi.ezsport.dtos.field.CreateFieldRequest;
import vn.hust.hedspi.ezsport.dtos.field.FieldResponse;
import vn.hust.hedspi.ezsport.dtos.field.UpdateFieldRequest;
import vn.hust.hedspi.ezsport.entities.Field;
import vn.hust.hedspi.ezsport.entities.Sport;
import vn.hust.hedspi.ezsport.entities.User;
import vn.hust.hedspi.ezsport.exceptions.AppException;
import vn.hust.hedspi.ezsport.exceptions.ErrorCode;
import vn.hust.hedspi.ezsport.mappers.FieldMapper;
import vn.hust.hedspi.ezsport.repositories.FieldRepository;
import vn.hust.hedspi.ezsport.repositories.SportRepository;
import vn.hust.hedspi.ezsport.repositories.UserRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class FieldService {
    FieldRepository fieldRepository;

    UserRepository userRepository;

    SportRepository sportRepository;

    FieldMapper fieldMapper;

    public ApiResponse<Page<FieldResponse>> listField(Pageable pageable) {
        ApiResponse response = new ApiResponse();
        Page<Field> fieldPage = fieldRepository.findAll(pageable);
        Page<FieldResponse> fieldResponsePage = fieldPage.map(fieldMapper::toFieldResponse);
        response.setMessage("Get list field successful !");
        response.setResult(fieldResponsePage);

        return response;
    }

    public ApiResponse<FieldResponse> createField(CreateFieldRequest request,String userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_FOUND)
        );
        Sport sport = sportRepository.findById(request.getSportId()).orElseThrow(
                ()-> new AppException(ErrorCode.FIELD_NOT_FOUND)
        );

        ApiResponse<FieldResponse> response = new ApiResponse<FieldResponse>();

        Field field = fieldMapper.toCreateFieldRequest(request);
        field.setOwner(user);
        field.setSport(sport);
        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(new Coordinate(request.getLongitude(), request.getLatitude()));
        field.setLocation(point);
        FieldResponse fieldResponse = fieldMapper.toFieldResponse(fieldRepository.save(field));
        response.setMessage("Create field successful !");
        response.setResult(fieldResponse);

        return response;
    }

    public ApiResponse<FieldResponse> getFieldById(String id) {
        Field field = fieldRepository.findById(id).orElse(null);
        FieldResponse fieldResponse = fieldMapper.toFieldResponse(field);
        ApiResponse response = new ApiResponse();
        response.setMessage("Get field successful !");
        response.setResult(fieldResponse);

        return response;
    }

    public ApiResponse<FieldResponse> updateField(String id, UpdateFieldRequest request) {
        Field field = fieldRepository.findById(id).orElse(null);
        if (field == null) {
            ApiResponse response = new ApiResponse();
            response.setCode(404);
            response.setMessage("Field not found !");

            return response;
        }
        User user = userRepository.findById(request.getOwnerId()).orElse(null);
        if (user == null) {
            ApiResponse response = new ApiResponse();
            response.setCode(404);
            response.setMessage("User not found !");

            return response;
        }
        Sport sport = sportRepository.findById(request.getSportId()).orElse(null);
        if (sport == null) {
            ApiResponse response = new ApiResponse();
            response.setCode(404);
            response.setMessage("Sport not found !");

            return response;
        }
        field.setName(request.getName());
        field.setIsActive(request.getIsActive());
        field.setDescription(request.getDescription());
        field.setAvatar(request.getAvatar());
        field.setOwner(user);
        field.setSport(sport);
        field.setStatus(request.getStatus());
        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(new Coordinate(request.getLongitude(), request.getLatitude()));
        field.setLocation(point);
        FieldResponse fieldResponse = fieldMapper.toFieldResponse(fieldRepository.save(field));
        ApiResponse response = new ApiResponse();
        response.setMessage("Update field successful !");
        response.setResult(fieldResponse);

        return response;
    }

    public ApiResponse<Void> deleteField(String id) {
        Field field = fieldRepository.findById(id).orElse(null);
        ApiResponse response = new ApiResponse();
        if (field == null) {
            response.setCode(404);
            response.setMessage("Field not found !");

            return response;
        }
        fieldRepository.delete(field);
        response.setMessage("Delete field successful !");

        return response;
    }

    public void seedFields() {
        long count = fieldRepository.count();
        if(count < 10){
            FieldData data = new FieldData(userRepository, sportRepository);
            fieldRepository.saveAll(data.generate(100));
        }
    }
}
