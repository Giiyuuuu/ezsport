package vn.hust.hedspi.ezsport.mappers;


import org.mapstruct.Mapper;
import vn.hust.hedspi.ezsport.dtos.feed.CreateFeedRequest;
import vn.hust.hedspi.ezsport.dtos.feed.FeedResponse;
import vn.hust.hedspi.ezsport.entities.Feed;

@Mapper(componentModel = "spring")
public interface FeedMapper {
    Feed toCreateFeedRequest(CreateFeedRequest request);

    FeedResponse toFeedResponse(Feed feed);
}
