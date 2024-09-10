package vn.hust.hedspi.ezsport.domain.mappers;


import org.mapstruct.Mapper;
import vn.hust.hedspi.ezsport.domain.dtos.feed.CreateFeedRequest;
import vn.hust.hedspi.ezsport.domain.dtos.feed.FeedResponse;
import vn.hust.hedspi.ezsport.database.entities.Feed;

@Mapper(componentModel = "spring")
public interface FeedMapper {
    Feed toCreateFeedRequest(CreateFeedRequest request);

    FeedResponse toFeedResponse(Feed feed);
}
