package vn.hust.hedspi.ezsport.services;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.hust.hedspi.ezsport.data.FieldOrderData;
import vn.hust.hedspi.ezsport.dtos.ApiResponse;
import vn.hust.hedspi.ezsport.dtos.fieldorder.CreateFieldOrderRequest;
import vn.hust.hedspi.ezsport.dtos.fieldorder.FieldOrderResponse;
import vn.hust.hedspi.ezsport.dtos.fieldorder.UpdateFieldOrderRequest;
import vn.hust.hedspi.ezsport.entities.Field;
import vn.hust.hedspi.ezsport.entities.FieldOrder;
import vn.hust.hedspi.ezsport.entities.User;
import vn.hust.hedspi.ezsport.mappers.FieldOrderMapper;
import vn.hust.hedspi.ezsport.repositories.FieldOrderRepository;
import vn.hust.hedspi.ezsport.repositories.FieldRepository;
import vn.hust.hedspi.ezsport.repositories.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class FieldOrderService {
    @Autowired
    FieldOrderRepository fieldOrderRepository;

    @Autowired
    FieldRepository fieldRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FieldOrderMapper fieldOrderMapper;

    public ApiResponse<Page<FieldOrderResponse>> listFieldOrder(Pageable pageable) {
        ApiResponse response = new ApiResponse();
        Page<FieldOrder> fieldOrderPage = fieldOrderRepository.findAll(pageable);
        Page<FieldOrderResponse> fieldOrderResponsePage = fieldOrderPage.map(fieldOrderMapper::toFieldOrderResponse);
        response.setMessage("Get list field order successful !");
        response.setResult(fieldOrderResponsePage);

        return response;
    }

//    public ApiResponse<FieldOrderResponse> createFieldOrder(CreateFieldOrderRequest request) {
//        Field field = fieldRepository.findById(request.getFieldId()).orElse(null);
//        if (field == null) {
//            ApiResponse response = new ApiResponse();
//            response.setCode(404);
//            response.setMessage("Field not found !");
//
//            return response;
//        }
//        if (request.getStart().isAfter(request.getEnd())){
//            ApiResponse response = new ApiResponse();
//            response.setCode(400);
//            response.setMessage("Start time must be before end time !");
//
//            return response;
//        }
//        if (request.getUserId() != null) {
//            User user = userRepository.findById(request.getUserId()).orElse(null);
//            if (user == null) {
//                ApiResponse response = new ApiResponse();
//                response.setCode(404);
//                response.setMessage("User not found !");
//
//                return response;
//            }
//        }
//        FieldOrder fieldOrder = fieldOrderMapper.toCreateFieldOrderRequest(request);
//        fieldOrder.setField(field);
//        FieldOrderResponse fieldOrderResponse = fieldOrderMapper.toFieldOrderResponse(fieldOrderRepository.save(fieldOrder));
//        ApiResponse response = new ApiResponse();
//        response.setMessage("Create field order successful !");
//        response.setResult(fieldOrderResponse);
//
//        return response;
//    }

    public ApiResponse<FieldOrderResponse> getFieldOrderById(String id) {
        FieldOrder fieldOrder = fieldOrderRepository.findById(id).orElse(null);
        FieldOrderResponse fieldOrderResponse = fieldOrderMapper.toFieldOrderResponse(fieldOrder);
        ApiResponse response = new ApiResponse();
        response.setMessage("Get field order successful !");
        response.setResult(fieldOrderResponse);

        return response;
    }

    public ApiResponse<FieldOrderResponse> updateFieldOrder(String id, UpdateFieldOrderRequest request) {
        FieldOrder fieldOrder = fieldOrderRepository.findById(id).orElse(null);
        if (fieldOrder == null) {
            ApiResponse response = new ApiResponse();
            response.setCode(404);
            response.setMessage("Field order not found !");

            return response;
        }
        Field field = fieldRepository.findById(request.getFieldId()).orElse(null);
        if (field == null) {
            ApiResponse response = new ApiResponse();
            response.setCode(404);
            response.setMessage("Field not found !");

            return response;
        }
        if (request.getStart().isAfter(request.getEnd())){
            ApiResponse response = new ApiResponse();
            response.setCode(400);
            response.setMessage("Start time must be before end time !");

            return response;
        }
        User user = userRepository.findById(request.getUserId()).orElse(null);
        if (user == null) {
            ApiResponse response = new ApiResponse();
            response.setCode(404);
            response.setMessage("User not found !");

            return response;
        }
        fieldOrder.setStart(request.getStart());
        fieldOrder.setEnd(request.getEnd());
        fieldOrder.setDate(request.getDate());
        fieldOrder.setPrice(request.getPrice());
        fieldOrder.setUserId(request.getUserId());
        fieldOrder.setPaidAt(request.getPaidAt());
        fieldOrder.setField(field);
        FieldOrderResponse fieldOrderResponse = fieldOrderMapper.toFieldOrderResponse(fieldOrderRepository.save(fieldOrder));
        ApiResponse response = new ApiResponse();
        response.setMessage("Update field order successful !");
        response.setResult(fieldOrderResponse);

        return response;
    }

    public ApiResponse<Void> deleteFieldOrder(String id) {
        FieldOrder fieldOrder = fieldOrderRepository.findById(id).orElse(null);
        if (fieldOrder == null) {
            ApiResponse response = new ApiResponse();
            response.setCode(404);
            response.setMessage("Field order not found !");

            return response;
        }
        fieldOrderRepository.delete(fieldOrder);
        ApiResponse response = new ApiResponse();
        response.setMessage("Delete field order successful !");

        return response;
    }
}
