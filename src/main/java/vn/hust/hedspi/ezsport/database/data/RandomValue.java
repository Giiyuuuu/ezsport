package vn.hust.hedspi.ezsport.database.data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class RandomValue {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateRandomString(int minLength, int maxLength) {
        Random random = new Random();
        int length = random.nextInt((maxLength - minLength) + 1) + minLength;

        StringBuilder randomString = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            randomString.append(CHARACTERS.charAt(randomIndex));
        }

        return randomString.toString();
    }

    /**
     * Get random longitude
     * */
    public static double generateRandomLongitude(){
        double min = -180.0;
        double max = 180.0;
        return min + (max - min) * Math.random();
    }
    /**
     * Get random latitude
     * */
    public static double generateRandomLatitude(){
        double min = -90.0;
        double max = 90.0;
        return min + (max - min) * Math.random();
    }

    public static LocalDate generateRandomDate(LocalDate startDate,LocalDate endDate){
        long daysBetween = ChronoUnit.DAYS.between(startDate,endDate);
        long randomDays = new Random().nextInt((int) daysBetween + 1);
        return startDate.plusDays(randomDays);
    }

    public static LocalTime generateRandomTime(){
        long randomHour = new Random().nextInt(24);
        return LocalTime.of((int)randomHour,0);
    }
}
