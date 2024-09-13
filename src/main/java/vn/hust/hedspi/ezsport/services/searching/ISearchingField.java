package vn.hust.hedspi.ezsport.services.searching;

import org.locationtech.jts.geom.Point;
import vn.hust.hedspi.ezsport.database.entities.Field;

import java.util.List;

public interface ISearchingField {
    List<Field> searchField(String fieldName, Point point, double radius);
}
