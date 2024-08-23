package vn.hust.hedspi.ezsport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hust.hedspi.ezsport.entities.FieldOrder;

import java.util.UUID;

@Repository
public interface FieldOrderRepository extends JpaRepository<FieldOrder, String> {
}
