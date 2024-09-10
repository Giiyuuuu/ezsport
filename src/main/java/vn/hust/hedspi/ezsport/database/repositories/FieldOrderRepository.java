package vn.hust.hedspi.ezsport.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hust.hedspi.ezsport.database.entities.FieldOrder;

@Repository
public interface FieldOrderRepository extends JpaRepository<FieldOrder, String> {
}
