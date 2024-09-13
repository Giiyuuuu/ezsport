package vn.hust.hedspi.ezsport.services.searching.searchinginterface;

import org.locationtech.jts.geom.Point;
import vn.hust.hedspi.ezsport.domain.dtos.user.UserSearchingResponseProjection;

import java.util.List;

public interface ISearchingUser {
    List<UserSearchingResponseProjection> searchUser(String username, Point location, double radius);
}
