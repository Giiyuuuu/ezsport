package vn.hust.hedspi.ezsport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.hust.hedspi.ezsport.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "SELECT * FROM users",nativeQuery = true)
    List<User> getRandom1000User();

    @Query(value = "SELECT * FROM users ORDER BY RANDOM() LIMIT 10000",nativeQuery = true)
    List<User> getRandom10000User();
}
