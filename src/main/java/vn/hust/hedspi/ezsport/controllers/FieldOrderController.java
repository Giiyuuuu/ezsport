package vn.hust.hedspi.ezsport.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import vn.hust.hedspi.ezsport.dtos.ApiResponse;
import vn.hust.hedspi.ezsport.dtos.fieldorder.CreateFieldOrderRequest;
import vn.hust.hedspi.ezsport.dtos.fieldorder.FieldOrderResponse;
import vn.hust.hedspi.ezsport.dtos.fieldorder.UpdateFieldOrderRequest;
import vn.hust.hedspi.ezsport.services.FieldOrderService;

@RestController
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("api/v1/field-order")
public class FieldOrderController {
    @Autowired
    private FieldOrderService fieldOrderService;

    //List field order
    @GetMapping()
    public ApiResponse<Page<FieldOrderResponse>> listFieldOrder(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1000") int size
    ){
        Pageable pageable = PageRequest.of(page, size);

        return fieldOrderService.listFieldOrder(pageable);
    }

    //Create
    @PostMapping()
    public ApiResponse<FieldOrderResponse> createFieldOrder(@Valid @RequestBody CreateFieldOrderRequest requestBody){
        return fieldOrderService.createFieldOrder(requestBody);
    }

    //Show
    @GetMapping("/{id}")
    public ApiResponse<FieldOrderResponse> getFieldOrderById(@PathVariable String id){
        return fieldOrderService.getFieldOrderById(id);
    }

    //Update
    @PutMapping("/{id}")
    public ApiResponse<FieldOrderResponse> updateFieldOrder(@PathVariable String id,@Valid @RequestBody UpdateFieldOrderRequest requestBody){
        return fieldOrderService.updateFieldOrder(id, requestBody);
    }

    //Delete
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteFieldOrder(@PathVariable String id){
        return fieldOrderService.deleteFieldOrder(id);
    }
}
