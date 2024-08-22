package vn.hust.hedspi.ezsport.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "player_bookings")
public class PlayerBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @ManyToOne
    @JoinColumn(name = "booking_user",referencedColumnName = "id",nullable = false)
    User bookingUser;

    @ManyToOne
    @JoinColumn(name = "booked_user",referencedColumnName = "id",nullable = false)
    User bookedUser;

    @ManyToOne
    @JoinColumn(name = "feed_id",referencedColumnName = "id",nullable = false)
    Feed feed;

    @Column()
    String status = "waiting";
}
