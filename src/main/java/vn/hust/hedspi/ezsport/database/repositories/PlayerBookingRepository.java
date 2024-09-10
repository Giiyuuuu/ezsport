package vn.hust.hedspi.ezsport.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hust.hedspi.ezsport.database.entities.PlayerBooking;

@Repository
public interface PlayerBookingRepository extends JpaRepository<PlayerBooking, String> {
}
