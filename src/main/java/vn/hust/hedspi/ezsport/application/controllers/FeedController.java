package vn.hust.hedspi.ezsport.application.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import vn.hust.hedspi.ezsport.domain.dtos.ApiResponse;
import vn.hust.hedspi.ezsport.domain.dtos.feed.CreateFeedRequest;
import vn.hust.hedspi.ezsport.domain.dtos.feed.FeedResponse;
import vn.hust.hedspi.ezsport.domain.dtos.feed.UpdateFeedRequest;
import vn.hust.hedspi.ezsport.services.FeedService;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("api/v1/feed")
public class FeedController {
    @Autowired
    private FeedService feedService;

    //List
    @GetMapping()
    public ApiResponse<Page<FeedResponse>> listFeed(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1000") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        return feedService.listFeed(pageable);
    }

    //Create
    @PostMapping()
    public ApiResponse<FeedResponse> createFeed(@Valid @RequestBody CreateFeedRequest requestBody){
        return feedService.createFeed(requestBody);
    }

    //Show
    @GetMapping("/{id}")
    public ApiResponse<FeedResponse> getFeedById(@PathVariable String id){
        return feedService.getFeedById(id);
    }

    //Update
    @PutMapping("/{id}")
    public ApiResponse<FeedResponse> updateFeed(@PathVariable String id, @Valid @RequestBody UpdateFeedRequest requestBody){
        return feedService.updateFeed(id, requestBody);
    }

    //Delete
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteFeed(@PathVariable String id){
        return feedService.deleteFeed(id);
    }
}
