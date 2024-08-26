package vn.hust.hedspi.ezsport.services;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.hust.hedspi.ezsport.entities.Sport;
import vn.hust.hedspi.ezsport.repositories.SportRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class SportService {
    final List<String> sports = new ArrayList<>(Arrays.asList("football","badminton","volleyball","pickleball","tennis","basketball","baseball","table-tennis")) ;

    SportRepository sportRepository;

    @PostConstruct
    public void init(){
        long length = sportRepository.count();

        sports.forEach(sportName->{
            Optional<Sport> sport = sportRepository.findOneByName(sportName);
            if(sport.isEmpty()) {
                Sport sport1 = new Sport();
                sport1.setName(sportName);
                sportRepository.save(sport1);
            }
        });
    }
}
