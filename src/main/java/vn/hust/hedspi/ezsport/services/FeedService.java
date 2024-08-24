package vn.hust.hedspi.ezsport.services;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.hust.hedspi.ezsport.data.FeedData;
import vn.hust.hedspi.ezsport.entities.Feed;
import vn.hust.hedspi.ezsport.repositories.FeedRepository;
import vn.hust.hedspi.ezsport.repositories.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class FeedService {
    FeedRepository feedRepository;
    UserRepository userRepository;

    @PostConstruct
    public void init(){
        long count = feedRepository.count();
        if(count<10){
            log.info("Generate so many feeds ...");
            FeedData feedData = new FeedData(userRepository);
            List<Feed> feeds =  feedData.generate(1);
            feedRepository.saveAll(feeds);
        }
    }
}
