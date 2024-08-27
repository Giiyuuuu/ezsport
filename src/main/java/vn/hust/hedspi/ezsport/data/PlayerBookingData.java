package vn.hust.hedspi.ezsport.data;

import lombok.AllArgsConstructor;
import vn.hust.hedspi.ezsport.entities.Feed;
import vn.hust.hedspi.ezsport.entities.PlayerBooking;
import vn.hust.hedspi.ezsport.entities.User;
import vn.hust.hedspi.ezsport.repositories.FeedRepository;
import vn.hust.hedspi.ezsport.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
public class PlayerBookingData implements DataSeeder<PlayerBooking> {
    private FeedRepository feedRepository;

    private UserRepository userRepository;

    @Override
    public List<PlayerBooking> generate(int amount) {
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

        return randomPlayerBookings;
    }
}
