package vn.hust.hedspi.ezsport.data;

import lombok.AllArgsConstructor;
import vn.hust.hedspi.ezsport.entities.Field;
import vn.hust.hedspi.ezsport.entities.FieldOrder;
import vn.hust.hedspi.ezsport.entities.Sport;
import vn.hust.hedspi.ezsport.repositories.FieldRepository;
import vn.hust.hedspi.ezsport.repositories.SportRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
public class FieldOrderData implements DataSeeder<FieldOrder>{
    FieldRepository fieldRepository;

    @Override
    public List<FieldOrder> generate(int amount) {
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

        return randomFieldOrders;
    }
}
