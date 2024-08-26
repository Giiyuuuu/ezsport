package vn.hust.hedspi.ezsport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hust.hedspi.ezsport.entities.Sport;
import java.util.Optional;


@Repository
public interface SportRepository extends JpaRepository<Sport, String> {
    Optional<Sport> findByName(String name);
}
