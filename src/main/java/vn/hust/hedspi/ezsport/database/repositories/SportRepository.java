package vn.hust.hedspi.ezsport.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hust.hedspi.ezsport.database.entities.Sport;
import java.util.Optional;

@Repository
public interface SportRepository extends JpaRepository<Sport, Integer> {
    Optional<Sport> findOneByName(String name);
}
