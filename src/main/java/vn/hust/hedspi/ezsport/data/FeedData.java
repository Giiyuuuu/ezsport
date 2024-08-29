package vn.hust.hedspi.ezsport.data;


import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateSequence;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import vn.hust.hedspi.ezsport.entities.Feed;
import vn.hust.hedspi.ezsport.entities.User;
import vn.hust.hedspi.ezsport.repositories.UserRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;


@AllArgsConstructor
public class FeedData implements DataSeeder<Feed> {
    private UserRepository userRepository;

    @Override
    public List<Feed> generate(int amount) {
        List<Feed> randomFeeds = new java.util.ArrayList<>(List.of());

        Random random = new Random();
        GeometryFactory geometryFactory = new GeometryFactory();
        for(int i=0;i<amount;i++){
            List<User> users = userRepository.getRandom1000User();
            users.forEach(user->{
                Feed feed = new Feed();
                feed.setUser(user);
                feed.setDescription(RandomValue.generateRandomString(10,100));
                feed.setDate(RandomValue.generateRandomDate(LocalDate.of(2024, 1, 1),LocalDate.now()));
                LocalTime startTime = RandomValue.generateRandomTime();
                LocalTime endTime = startTime.plusHours(random.nextInt(3)+1);

                Point point = geometryFactory.createPoint(new Coordinate(RandomValue.generateRandomLatitude(),RandomValue.generateRandomLongitude()));
                feed.setLocation(point);
                feed.setStart(startTime);
                feed.setEnd(endTime);

                randomFeeds.add(feed);
            });
        }

        return randomFeeds;
    }
}
