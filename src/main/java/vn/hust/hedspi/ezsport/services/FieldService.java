package vn.hust.hedspi.ezsport.services;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.hust.hedspi.ezsport.dtos.ApiResponse;
import vn.hust.hedspi.ezsport.dtos.field.CreateFieldRequest;
import vn.hust.hedspi.ezsport.dtos.field.FieldResponse;
import vn.hust.hedspi.ezsport.dtos.field.UpdateFieldRequest;
import vn.hust.hedspi.ezsport.entities.Field;
import vn.hust.hedspi.ezsport.entities.Sport;
import vn.hust.hedspi.ezsport.entities.User;
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

    public ApiResponse<FieldResponse> createField(CreateFieldRequest request) {
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
        Field field = fieldMapper.toCreateFieldRequest(request);
        field.setOwner(user);
        field.setSport(sport);
        FieldResponse fieldResponse = fieldMapper.toFieldResponse(fieldRepository.save(field));
        ApiResponse response = new ApiResponse();
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
        field.setBlock(request.getBlock());
        field.setLongitude(request.getLongitude());
        field.setLatitude(request.getLatitude());
        field.setDescription(request.getDescription());
        field.setAvatar(request.getAvatar());
        field.setOwner(user);
        field.setSport(sport);
        field.setStatus(request.getStatus());
        FieldResponse fieldResponse = fieldMapper.toFieldResponse(fieldRepository.save(field));
        ApiResponse response = new ApiResponse();
        response.setMessage("Update field successful !");
        response.setResult(fieldResponse);

        return response;
    }

    public ApiResponse<Void> deleteField(String id) {
        Field field = fieldRepository.findById(id).orElse(null);
        if (field == null) {
            ApiResponse response = new ApiResponse();
            response.setCode(404);
            response.setMessage("Field not found !");

            return response;
        }
        fieldRepository.delete(field);
        ApiResponse response = new ApiResponse();
        response.setMessage("Delete field successful !");

        return response;
    }
}
