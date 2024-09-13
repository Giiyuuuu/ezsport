package vn.hust.hedspi.ezsport.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.hust.hedspi.ezsport.database.entities.Field;

import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<Field, String> {
    @Query(value = "SELECT * FROM fields ORDER BY RANDOM() LIMIT 10000", nativeQuery = true)
    List<Field> getRandoms10000Fields();

    @Query(value = "SELECT * FROM fields f WHERE f.owner_id = :ownerId;", nativeQuery = true)
    List<Field> getFieldsByOwnerId(@Param("ownerId") String ownerId);

    @Query(value = "SELECT * FROM fields f WHERE f.name LIKE '%:name%;'", nativeQuery = true)
    List<Field> findByName(@Param("name") String name);
}
