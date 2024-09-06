package vn.hust.hedspi.ezsport.dtos.fieldorder;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateListFieldOrderRequest {
    @NotNull
    String fieldId;

    List<CreateFieldOrderRequest> listFieldOrder;
}
