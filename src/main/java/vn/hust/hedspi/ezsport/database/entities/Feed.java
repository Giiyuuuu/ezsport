package vn.hust.hedspi.ezsport.database.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.locationtech.jts.geom.Point;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "feeds")
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @ManyToOne()
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    User user;

    @Column()
    String description;

    @Column(nullable = false,name = "start_time")
    LocalTime start;

    @Column(nullable = false,name = "end_time")
    LocalTime end;

    @Column(nullable = false,name = "feed_date")
    LocalDate date;

    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'looking'")
    String status = "looking";

    @Column(columnDefinition = "GEOMETRY")
    Point location;

    @ManyToOne
    @JoinColumn(name = "sport_id",referencedColumnName = "id")
    private Sport sport;
}

