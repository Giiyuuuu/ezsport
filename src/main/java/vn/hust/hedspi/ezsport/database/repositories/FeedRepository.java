package vn.hust.hedspi.ezsport.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.hust.hedspi.ezsport.database.entities.Feed;

import java.util.List;

@Repository
public interface FeedRepository extends JpaRepository<Feed, String> {
    @Query(value = "SELECT * FROM feeds ORDER BY RANDOM() LIMIT 10000", nativeQuery = true)
    List<Feed> getRandomFeedsLimit();

//    @Modifying
//    @Transactional
//    @Query(value = "INSERT INTO feeds (id,description, start_time, end_time, feed_date, location,user_id) " +
//            "VALUES (:id,:description, :startTime, :endTime, :date, ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326),:userId)",
//            nativeQuery = true)
//    int insertFeed(
//            @Param("id") String id,
//            @Param("description") String description,
//            @Param("startTime") LocalTime startTime,
//            @Param("endTime") LocalTime endTime,
//            @Param("date") LocalDate date,
//            @Param("longitude") double longitude,
//            @Param("latitude") double latitude,
//            @Param("userId") String userId);
}
