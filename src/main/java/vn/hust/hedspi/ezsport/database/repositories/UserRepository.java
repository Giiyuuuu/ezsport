package vn.hust.hedspi.ezsport.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.hust.hedspi.ezsport.database.entities.User;
import vn.hust.hedspi.ezsport.domain.dtos.user.UserSearchingResponseProjection;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "SELECT * FROM users",nativeQuery = true)
    List<User> getRandom1000User();

    @Query(value = "SELECT * FROM users ORDER BY RANDOM() LIMIT 10000",nativeQuery = true)
    List<User> getRandom10000User();

    @Query(value = "SELECT * FROM users ORDER BY RANDOM() LIMIT 2",nativeQuery = true)
    List<User> getRandom2User();

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT id,first_name,last_name,email FROM users WHERE first_name LIKE %:name% OR last_name LIKE %:name% LIMIT 10",nativeQuery = true)
    List<UserSearchingResponseProjection> findByName(@Param("name") String name);
}
