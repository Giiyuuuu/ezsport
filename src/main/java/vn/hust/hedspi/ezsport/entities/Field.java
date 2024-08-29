package vn.hust.hedspi.ezsport.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fields")
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column()
    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id",referencedColumnName = "id")
    private User owner;

    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'waiting'")
    private String isActive; // waiting , active, inactive

    @Column(columnDefinition = "GEOMETRY")
    private Point location;

    @Column()
    private String description;

    @Column()
    private String avatar;

    @Column()
    private String status;

    @ManyToOne
    @JoinColumn(name = "sport_id",referencedColumnName = "id")
    private Sport sport;
}
