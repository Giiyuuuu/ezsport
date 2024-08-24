package vn.hust.hedspi.ezsport.data;

import vn.hust.hedspi.ezsport.entities.Field;

import java.util.List;

public class FieldData implements DataSeeder<Field> {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    @Override
    public List<Field> generate(int amount) {
        return List.of();
    }
}
