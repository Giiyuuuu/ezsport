package vn.hust.hedspi.ezsport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hust.hedspi.ezsport.entities.PlayerBooking;

import java.util.UUID;

public interface PlayerBookingRepository extends JpaRepository<PlayerBooking, String> {
}
