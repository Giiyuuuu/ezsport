package vn.hust.hedspi.ezsport.domain.dtos.field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import vn.hust.hedspi.ezsport.database.entities.Sport;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class FieldResponse {
    String id;
    String name;
//    User owner;
    String isActive;
    String description;
    String status;
//    Point location;
    double longitude;
    double latitude;
    String avatar;
    Sport sport;
}
