package vn.hust.hedspi.ezsport.data;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import vn.hust.hedspi.ezsport.entities.Sport;
import vn.hust.hedspi.ezsport.repositories.SportRepository;

import java.util.List;

@AllArgsConstructor
@Component
public class SportData implements ISeeder {
    @Autowired
    private SportRepository sportRepository;

    @Autowired
    private ConfigurableApplicationContext context;

    @Override
    public void seed(int amount) {
        List<Sport> randomSports = new java.util.ArrayList<>(List.of());
        List<String> sports = List.of("Football", "Basketball", "Volleyball", "Tennis", "Badminton", "Table Tennis", "Swimming", "Running", "Cycling", "Gym", "Yoga", "Dance", "Boxing", "Karate", "Taekwondo", "Judo", "Wrestling", "Fencing", "Archery", "Shooting", "Golf", "Billiards", "Chess", "Poker", "Bowling", "Darts", "Rugby", "Hockey", "Baseball", "Softball", "Cricket", "Soccer", "Handball", "Water Polo", "Rowing", "Canoeing", "Kayaking", "Sailing", "Surfing", "Skateboarding", "Skiing", "Snowboarding", "Ice Skating", "Ice Hockey", "Bobsleigh", "Luge", "Skeleton", "Biathlon", "Triathlon", "Decathlon", "Heptathlon", "Pentathlon", "Tetradecathlon", "Aquathlon", "Duathlon", "Tetrathlon", "Modern Pentathlon", "Quadrathlon", "Orienteering", "Adventure Racing", "CrossFit", "Powerlifting", "Weightlifting", "Bodybuilding", "Strongman", "Arm Wrestling", "Curling", "Croquet", "Petanque", "Bocce", "Lawn Bowls", "Horseshoes", "Cornhole", "Kubb", "Spikeball", "Tug of War", "Ultimate Frisbee", "Disc Golf", "Footgolf", "Footvolley", "Sepak Takraw", "Kabaddi", "Kho Kho", "Gilli Danda", "Jallikattu", "Bull Racing", "Bull Fighting", "Bull Leaping");
        sports.forEach(sport->{
            Sport sportData = new Sport();
            sportData.setName(sport);

            randomSports.add(sportData);
        });

        sportRepository.saveAll(randomSports);

        context.getBeanFactory().destroyBean(this);
    }

}
