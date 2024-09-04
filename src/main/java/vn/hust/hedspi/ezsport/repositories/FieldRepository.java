package vn.hust.hedspi.ezsport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.hust.hedspi.ezsport.entities.Field;

import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<Field, String> {
    @Query(value = "SELECT * FROM fields ORDER BY RANDOM() LIMIT 10000", nativeQuery = true)
    List<Field> getRandoms10000Fields();
}
