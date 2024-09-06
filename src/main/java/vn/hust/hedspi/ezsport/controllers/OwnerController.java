package vn.hust.hedspi.ezsport.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import vn.hust.hedspi.ezsport.dtos.ApiResponse;
import vn.hust.hedspi.ezsport.dtos.field.CreateFieldRequest;
import vn.hust.hedspi.ezsport.dtos.field.FieldResponse;
import vn.hust.hedspi.ezsport.dtos.fieldorder.CreateListFieldOrderRequest;
import vn.hust.hedspi.ezsport.dtos.fieldorder.FieldOrderResponse;
import vn.hust.hedspi.ezsport.entities.Field;
import vn.hust.hedspi.ezsport.services.OwnerService;

import java.util.List;


@RestController()
@RequestMapping("api/v1/owner")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@PreAuthorize("hasRole('OWNER')")
public class OwnerController {
    OwnerService ownerService;

    @GetMapping("/fields")
    public ApiResponse<List<FieldResponse>> getOwnFields(Authentication authentication){
        return ApiResponse.<List<FieldResponse>>builder()
                .result(ownerService.getOwnField(authentication.getName()))
                .build();
    }

    @PostMapping("/field")
    public ApiResponse<FieldResponse> createField(Authentication authentication, @RequestBody CreateFieldRequest request){
        return ApiResponse.<FieldResponse>builder()
                .result(ownerService.createField(request,authentication.getName()))
                .build();
    }

    @PostMapping("/create-order")
    public ApiResponse<List<FieldOrderResponse>> createOrder(Authentication authentication, @RequestBody CreateListFieldOrderRequest request){
        return ApiResponse.<List<FieldOrderResponse>>builder()
                .result(ownerService.createListFieldOrder(request,authentication.getName()))
                .build();
    }
}
