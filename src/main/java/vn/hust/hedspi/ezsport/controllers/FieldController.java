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
import vn.hust.hedspi.ezsport.dtos.field.CreateFieldRequest;
import vn.hust.hedspi.ezsport.dtos.field.FieldResponse;
import vn.hust.hedspi.ezsport.dtos.field.UpdateFieldRequest;
import vn.hust.hedspi.ezsport.services.FieldService;

@RestController()
@RequestMapping("api/v1/field")
@NoArgsConstructor
@AllArgsConstructor
public class FieldController {
    @Autowired
    private FieldService fieldService;

    @GetMapping()
    public ApiResponse<Page<FieldResponse>> listField(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1000") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        return fieldService.listField(pageable);
    }

//    @PostMapping()
//    public ApiResponse<FieldResponse> createField(@Valid @RequestBody CreateFieldRequest requestBody){
//        return fieldService.createField(requestBody);
//    }

    @GetMapping("/{id}")
    public ApiResponse<FieldResponse> getFieldById(@PathVariable String id){
        return fieldService.getFieldById(id);
    }

    @PutMapping("/{id}")
    public ApiResponse<FieldResponse> updateField(@PathVariable String id,@Valid @RequestBody UpdateFieldRequest requestBody){
        return fieldService.updateField(id, requestBody);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteField(@PathVariable String id){
        return fieldService.deleteField(id);
    }
}
