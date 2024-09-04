package vn.hust.hedspi.ezsport.dtos.sport;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SportResponse {
    private Integer id;
    private String name;
}
