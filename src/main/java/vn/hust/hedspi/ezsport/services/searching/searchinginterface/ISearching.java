package vn.hust.hedspi.ezsport.services.searching.searchinginterface;

import org.locationtech.jts.geom.Point;

import java.util.List;

public interface ISearching {
    List<Object> search(String keyword, Point point, double radius);
}
