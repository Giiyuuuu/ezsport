package vn.hust.hedspi.ezsport.database.data;


import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import vn.hust.hedspi.ezsport.database.entities.Feed;
import vn.hust.hedspi.ezsport.database.entities.User;
import vn.hust.hedspi.ezsport.database.repositories.FeedRepository;
import vn.hust.hedspi.ezsport.database.repositories.UserRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;


@Component
@AllArgsConstructor
public class FeedData implements ISeeder {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private ConfigurableApplicationContext context;

    @Override
    public void seed(int amount) {
        List<Feed> randomFeeds = new java.util.ArrayList<>(List.of());

        Random random = new Random();
        GeometryFactory geometryFactory = new GeometryFactory();
        for(int i=0;i<amount;i++){
            List<User> users = userRepository.getRandom10000User();
            users.forEach(user->{
                Feed feed = new Feed();
                feed.setUser(user);
                feed.setDescription(RandomValue.generateRandomString(10,100));
                feed.setDate(RandomValue.generateRandomDate(LocalDate.of(2024, 1, 1),LocalDate.now()));
                LocalTime startTime = RandomValue.generateRandomTime();
                LocalTime endTime = startTime.plusHours(random.nextInt(3)+1);

                Point point = geometryFactory.createPoint(new Coordinate(RandomValue.generateRandomLongitude(),RandomValue.generateRandomLatitude()));
                feed.setLocation(point);
                feed.setStart(startTime);
                feed.setEnd(endTime);

                randomFeeds.add(feed);
            });
        }

        feedRepository.saveAll(randomFeeds);

        context.getBeanFactory().destroyBean(this);
    }
}
