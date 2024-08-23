package vn.hust.hedspi.ezsport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hust.hedspi.ezsport.entities.Field;

import java.util.UUID;

@Repository
public interface FieldRepository extends JpaRepository<Field, UUID> {

}
