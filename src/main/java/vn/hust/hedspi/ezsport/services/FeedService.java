package vn.hust.hedspi.ezsport.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.hust.hedspi.ezsport.data.DataSeeder;
import vn.hust.hedspi.ezsport.data.FeedData;
import vn.hust.hedspi.ezsport.dtos.ApiResponse;
import vn.hust.hedspi.ezsport.dtos.feed.CreateFeedRequest;
import vn.hust.hedspi.ezsport.dtos.feed.FeedResponse;
import vn.hust.hedspi.ezsport.dtos.feed.UpdateFeedRequest;
import vn.hust.hedspi.ezsport.entities.Feed;
import vn.hust.hedspi.ezsport.entities.Sport;
import vn.hust.hedspi.ezsport.entities.User;
import vn.hust.hedspi.ezsport.mappers.FeedMapper;
import vn.hust.hedspi.ezsport.repositories.FeedRepository;
import vn.hust.hedspi.ezsport.repositories.SportRepository;
import vn.hust.hedspi.ezsport.repositories.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class FeedService {
    FeedRepository feedRepository;

    UserRepository userRepository;

    SportRepository sportRepository;

    FeedMapper feedMapper;

    public ApiResponse<Page<FeedResponse>> listFeed(Pageable pageable) {
        ApiResponse response = new ApiResponse();
        Page<Feed> feedPage = feedRepository.findAll(pageable);
        Page<FeedResponse> feedResponsePage = feedPage.map(feedMapper::toFeedResponse);
        response.setMessage("Get list feed successful !");
        response.setResult(feedResponsePage);

        return response;
    }

    public ApiResponse<FeedResponse> createFeed(CreateFeedRequest request) {
        User user = userRepository.findById(request.getUserId()).orElse(null);
        if (user == null) {
            ApiResponse response = new ApiResponse();
            response.setCode(404);
            response.setMessage("User not found !");

            return response;
        }
        Sport sport = sportRepository.findById(request.getSportId()).orElse(null);
        if (sport == null) {
            ApiResponse response = new ApiResponse();
            response.setCode(404);
            response.setMessage("Sport not found !");

            return response;
        }
        Feed feed = feedMapper.toCreateFeedRequest(request);
        feed.setUser(user);
        feed.setSport(sport);
        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(new Coordinate(request.getLongitude(), request.getLatitude()));
        feed.setLocation(point);
        FeedResponse feedResponse = feedMapper.toFeedResponse(feedRepository.save(feed));
        ApiResponse response = new ApiResponse();
        response.setMessage("Create feed successful !");
        response.setResult(feedResponse);

        return response;
    }

    public ApiResponse<FeedResponse> getFeedById(String id) {
        Feed feed = feedRepository.findById(id).orElse(null);
        FeedResponse feedResponse = feedMapper.toFeedResponse(feed);
        ApiResponse response = new ApiResponse();
        response.setMessage("Get feed successful !");
        response.setResult(feedResponse);

        return response;
    }

    public ApiResponse<FeedResponse> updateFeed(String id, UpdateFeedRequest request) {
        Feed feed = feedRepository.findById(id).orElse(null);
        if (feed == null) {
            ApiResponse response = new ApiResponse();
            response.setCode(404);
            response.setMessage("Feed not found !");

            return response;
        }
        User user = userRepository.findById(request.getUserId()).orElse(null);
        if (user == null) {
            ApiResponse response = new ApiResponse();
            response.setCode(404);
            response.setMessage("User not found !");

            return response;
        }
        Sport sport = sportRepository.findById(request.getSportId()).orElse(null);
        if (sport == null) {
            ApiResponse response = new ApiResponse();
            response.setCode(404);
            response.setMessage("Sport not found !");

            return response;
        }
        feed.setUser(user);
        feed.setSport(sport);
        feed.setDescription(request.getDescription());
        feed.setStart(request.getStart());
        feed.setEnd(request.getEnd());
        feed.setDate(request.getDate());
        feed.setStatus(request.getStatus());
        GeometryFactory geometryFactory = new GeometryFactory();
        Point point = geometryFactory.createPoint(new Coordinate(request.getLongitude(), request.getLatitude()));
        feed.setLocation(point);
        FeedResponse feedResponse = feedMapper.toFeedResponse(feedRepository.save(feed));
        ApiResponse response = new ApiResponse();
        response.setMessage("Update feed successful !");
        response.setResult(feedResponse);

        return response;
    }

    public ApiResponse<Void> deleteFeed(String id) {
        feedRepository.deleteById(id);
        ApiResponse response = new ApiResponse();
        response.setMessage("Delete feed successful !");
        return response;
    }

    public void seedFeeds(){
        long count = feedRepository.count();
        if(count<10){
            log.info("Generate so many feeds ...");
            DataSeeder<Feed> feedData = new FeedData(userRepository);
            List<Feed> feeds =  feedData.generate(1);
            feedRepository.saveAll(feeds);
//            feeds.forEach(feed->feedRepository.insertFeed(UUID.randomUUID().toString(),feed.getDescription(),feed.getStart(),feed.getEnd(),feed.getDate(),feed.getLocation().getX(),feed.getLocation().getY(),feed.getUser().getId()));
        }
    }
}
