package vn.hust.hedspi.ezsport.commands;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vn.hust.hedspi.ezsport.services.*;

@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Component
public class DataSeederCommand implements CommandLineRunner {
    // do this command to create package .jar file
    // ./mvnw package
    // add the argument seed-data when starting the application to seed data
    // e.g. java -jar target/ezsport-0.0.1-SNAPSHOT.jar seed-data

    FeedService feedService;
    UserService userService;
    PlayerBookingService playerBookingService;
    SportService sportService;
    FieldService fieldService;
    FieldOrderService fieldOrderService;

    @Override
    public void run(String... args) throws Exception {
        if (args.length > 0 && "seed-data".equals(args[0])) {
            seedAllTables();
        }
    }

    private void seedAllTables() {
        sportService.seedSports();
        userService.seedUsers();
        feedService.seedFeeds();
        playerBookingService.seedPlayerBookings();
        fieldService.seedFields();
        fieldOrderService.seedFieldOrders();
    }
}
