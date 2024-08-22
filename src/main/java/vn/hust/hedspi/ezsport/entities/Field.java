package vn.hust.hedspi.ezsport.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fields")
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column()
    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id",referencedColumnName = "id")
    private User owner;

    @Column()
    private String isActive = "waiting"; // waiting , active, inactive

    @Column()
    private String block;

    @Column()
    private double longitude;

    @Column()
    private double latitude;

    @Column()
    private String description;

    @Column()
    private String avatar;

    @Column()
    private String status;

    @Column(name = "sport_id")
    private UUID sport;
}
