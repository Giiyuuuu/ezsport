package vn.hust.hedspi.ezsport.domain.dtos.feed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import vn.hust.hedspi.ezsport.database.entities.Sport;
import vn.hust.hedspi.ezsport.database.entities.User;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class FeedResponse {
    String id;
    User user;
    String description;
    LocalTime start;
    LocalTime end;
    LocalDate date;
    String status;
//    Point location;
    Sport sport;
}
