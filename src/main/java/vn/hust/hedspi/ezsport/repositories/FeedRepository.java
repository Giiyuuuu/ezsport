package vn.hust.hedspi.ezsport.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hust.hedspi.ezsport.entities.Feed;

import java.util.UUID;

@Repository
public interface FeedRepository extends JpaRepository<Feed, String> {
}
