package vn.hust.hedspi.ezsport.data;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import vn.hust.hedspi.ezsport.entities.Field;
import vn.hust.hedspi.ezsport.entities.FieldOrder;
import vn.hust.hedspi.ezsport.repositories.FieldOrderRepository;
import vn.hust.hedspi.ezsport.repositories.FieldRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@AllArgsConstructor
public class FieldOrderData implements ISeeder {
    @Autowired
    private ConfigurableApplicationContext context;

    @Autowired
    FieldRepository fieldRepository;

    @Autowired
    FieldOrderRepository fieldOrderRepository;

    @Override
    public void seed(int amount) {
        List<FieldOrder> randomFieldOrders = new ArrayList<>();

        for (int i=0;i<amount;i++){
            List<Field> randomFields = fieldRepository.getRandoms10000Fields();

            Random random = new Random();

            randomFields.forEach(field -> {
                FieldOrder fieldOrder = new FieldOrder();
                fieldOrder.setField(field);
                LocalTime startTime = RandomValue.generateRandomTime();
                LocalTime endTime = startTime.plusHours(random.nextInt(3)+1);
                fieldOrder.setStart(startTime);
                fieldOrder.setEnd(endTime);
                fieldOrder.setDate(RandomValue.generateRandomDate(LocalDate.of(2024, 1, 1),LocalDate.now()));;
                fieldOrder.setPrice(random.nextDouble()*100);

                randomFieldOrders.add(fieldOrder);
            });
        }

        fieldOrderRepository.saveAll(randomFieldOrders);

        context.getBeanFactory().destroyBean(this);
    }
}
