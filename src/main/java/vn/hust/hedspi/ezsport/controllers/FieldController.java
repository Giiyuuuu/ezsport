package vn.hust.hedspi.ezsport.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.hust.hedspi.ezsport.dtos.field.CreateFieldRequest;
import vn.hust.hedspi.ezsport.dtos.field.UpdateFieldRequest;
import vn.hust.hedspi.ezsport.entities.Field;
import vn.hust.hedspi.ezsport.repositories.FieldRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("api/v1/field")
@NoArgsConstructor
@AllArgsConstructor
public class FieldController {
    @Autowired
    private FieldRepository fieldRepository;

    @GetMapping()
    public ResponseEntity<List<Field>> getAllFields(){
        return ResponseEntity.ok(fieldRepository.findAll());
    }

    @PostMapping()
    public ResponseEntity<Field> createField(@Valid @RequestBody CreateFieldRequest requestBody) {
        Field field = new Field();
        field.setName(requestBody.getName());
        field.setDescription(requestBody.getDescription());
        field.setLongitude(requestBody.getLongitude());
        field.setLatitude(requestBody.getLatitude());

        return ResponseEntity.ok(fieldRepository.save(field));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Field> getFieldById(@PathVariable UUID id){
        Optional<Field> field = fieldRepository.findById(id);

        return field.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Field> updateField(@PathVariable UUID id, @Valid @RequestBody UpdateFieldRequest requestBody) {
        Optional<Field> fieldOptional = fieldRepository.findById(id);
        if (fieldOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Field field = fieldOptional.get();
        field.setName(requestBody.getName());
        field.setDescription(requestBody.getDescription());
        field.setLongitude(requestBody.getLongitude());
        field.setLatitude(requestBody.getLatitude());

        return ResponseEntity.ok(fieldRepository.save(field));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteField(@PathVariable UUID id){
        Optional<Field> fieldOptional = fieldRepository.findById(id);
        if (fieldOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        fieldRepository.delete(fieldOptional.get());

        return ResponseEntity.noContent().build();
    }
}
