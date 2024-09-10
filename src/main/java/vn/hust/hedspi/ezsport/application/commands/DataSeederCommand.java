package vn.hust.hedspi.ezsport.application.commands;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import vn.hust.hedspi.ezsport.database.data.*;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class DataSeederCommand {
    // do this command to create package .jar file
    // ./mvnw package
    // add the argument seed-data when starting the application to seed data
    // e.g. java -jar target/ezsport-0.0.1-SNAPSHOT.jar seed-data

    FeedData feedData;
    UserData userData;
    PlayerBookingData playerBookingData;
    SportData sportData;
    FieldData fieldData;
    FieldOrderData fieldOrderData;
    ApplicationArguments args;

    /*
    * Make sure this function runs after all bean was constructed.
    * */
    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        if (args.containsOption("seed-data")) {
            System.out.println("DA VAO DAY");
            seedAllTables();
        }
    }

    private void seedAllTables() {
        sportData.seed(0);
        userData.seed(10);
        feedData.seed(100);
        playerBookingData.seed(100);
        fieldData.seed(100);
        fieldOrderData.seed(100);
    }
}
