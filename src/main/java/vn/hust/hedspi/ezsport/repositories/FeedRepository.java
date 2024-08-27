package vn.hust.hedspi.ezsport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.hust.hedspi.ezsport.entities.Feed;

import java.util.List;

@Repository
public interface FeedRepository extends JpaRepository<Feed, String> {
    @Query(value = "SELECT * FROM feeds ORDER BY RANDOM() LIMIT 10000", nativeQuery = true)
    List<Feed> getRandomFeedsLimit();
}
