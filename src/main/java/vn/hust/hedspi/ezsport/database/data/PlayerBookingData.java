package vn.hust.hedspi.ezsport.database.data;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import vn.hust.hedspi.ezsport.database.entities.Feed;
import vn.hust.hedspi.ezsport.database.entities.PlayerBooking;
import vn.hust.hedspi.ezsport.database.entities.User;
import vn.hust.hedspi.ezsport.database.repositories.FeedRepository;
import vn.hust.hedspi.ezsport.database.repositories.PlayerBookingRepository;
import vn.hust.hedspi.ezsport.database.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@AllArgsConstructor
public class PlayerBookingData implements ISeeder {
    @Autowired
    private FeedRepository feedRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlayerBookingRepository playerBookingRepository;

    @Autowired
    private ConfigurableApplicationContext context;

    @Override
    public void seed(int amount) {
        List<PlayerBooking> randomPlayerBookings = new ArrayList<>();
        for (int i=0;i<amount;i++){
            List<Feed> randomFeeds = feedRepository.getRandomFeedsLimit();
            List<User> users = userRepository.getRandom10000User();

            Random random = new Random();

            randomFeeds.forEach(feed -> {
                PlayerBooking playerBooking = new PlayerBooking();
                playerBooking.setFeed(feed);
                playerBooking.setBookingUser(feed.getUser());
                playerBooking.setBookedUser(users.get(random.nextInt(users.size())));

                randomPlayerBookings.add(playerBooking);
            });
        }
        playerBookingRepository.saveAll(randomPlayerBookings);


        context.getBeanFactory().destroyBean(this);
    }
}
