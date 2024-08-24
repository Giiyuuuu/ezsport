package vn.hust.hedspi.ezsport.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.UUID;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "sports")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false)
    String name;
}
