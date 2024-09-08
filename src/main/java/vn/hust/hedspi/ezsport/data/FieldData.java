package vn.hust.hedspi.ezsport.data;

import lombok.AllArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import vn.hust.hedspi.ezsport.entities.Field;
import vn.hust.hedspi.ezsport.entities.Sport;
import vn.hust.hedspi.ezsport.entities.User;
import vn.hust.hedspi.ezsport.repositories.FieldRepository;
import vn.hust.hedspi.ezsport.repositories.SportRepository;
import vn.hust.hedspi.ezsport.repositories.UserRepository;

import java.util.List;
import java.util.Random;

@Component
@AllArgsConstructor
public class FieldData implements ISeeder {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SportRepository sportRepository;

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private ConfigurableApplicationContext context;

    @Override
    public void seed(int amount) {
        List<Field> randomFields = new java.util.ArrayList<>(List.of());

        Random random = new Random();
        GeometryFactory geometryFactory = new GeometryFactory();
        List<Sport> sports = sportRepository.findAll();
        for (int i = 0; i < amount; i++) {
            List<User> users = userRepository.getRandom10000User();
            users.forEach(user->{
                Field field = new Field();
                field.setName(RandomValue.generateRandomString(10, 100));
                field.setOwner(user);
                field.setDescription(RandomValue.generateRandomString(10, 100));
                field.setAvatar(RandomValue.generateRandomString(10, 100));
                field.setStatus("active");
                field.setSport(sports.get(random.nextInt(sports.size())));
                Point point = geometryFactory.createPoint(new Coordinate(RandomValue.generateRandomLongitude(),RandomValue.generateRandomLatitude()));
                field.setLocation(point);

                randomFields.add(field);
            });
        }

        fieldRepository.saveAll(randomFields);

        context.getBeanFactory().destroyBean(this);
    }
}
