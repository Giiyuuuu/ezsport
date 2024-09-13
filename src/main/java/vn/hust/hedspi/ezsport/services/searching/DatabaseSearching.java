package vn.hust.hedspi.ezsport.services.searching;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Point;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import vn.hust.hedspi.ezsport.database.entities.Feed;
import vn.hust.hedspi.ezsport.database.entities.Field;
import vn.hust.hedspi.ezsport.database.repositories.FeedRepository;
import vn.hust.hedspi.ezsport.database.repositories.FieldRepository;
import vn.hust.hedspi.ezsport.database.repositories.UserRepository;
import vn.hust.hedspi.ezsport.domain.dtos.user.UserSearchingResponseProjection;
import vn.hust.hedspi.ezsport.services.searching.searchinginterface.ISearching;
import vn.hust.hedspi.ezsport.services.searching.searchinginterface.ISearchingFeed;
import vn.hust.hedspi.ezsport.services.searching.searchinginterface.ISearchingField;
import vn.hust.hedspi.ezsport.services.searching.searchinginterface.ISearchingUser;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
@Primary
public class DatabaseSearching implements ISearching, ISearchingField, ISearchingUser, ISearchingFeed {
    UserRepository userRepository;
    FieldRepository fieldRepository;
    FeedRepository feedRepository;

    @Override
    public List<Object> search(String keyword,Point point,double radius) {
        List<Object> searchingResults = new ArrayList<>();
        List<UserSearchingResponseProjection> users = userRepository.findByName(keyword);
        List<Field> fields = fieldRepository.findByName(keyword);
        searchingResults.addAll(users);
        searchingResults.addAll(fields);
        return searchingResults;
    }

    @Override
    public List<UserSearchingResponseProjection> searchUser(String username, Point location, double radius) {
        return userRepository.findByName(username);
    }

    @Override
    public List<Field> searchField(String fieldName,Point point,double radius) {
        return fieldRepository.findByName(fieldName);
    }

    @Override
    public List<Feed> searchFeed() {
        return List.of();
    }
}
