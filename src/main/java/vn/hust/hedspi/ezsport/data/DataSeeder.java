package vn.hust.hedspi.ezsport.data;

import java.util.List;

public interface DataSeeder<T> {
    public List<T> generate(int amount);
}
