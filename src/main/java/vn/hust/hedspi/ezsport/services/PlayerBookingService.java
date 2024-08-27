package vn.hust.hedspi.ezsport.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.hust.hedspi.ezsport.dtos.ApiResponse;
import vn.hust.hedspi.ezsport.dtos.playerBooking.CreatePlayerBookingRequest;
import vn.hust.hedspi.ezsport.dtos.playerBooking.PlayerBookingResponse;
import vn.hust.hedspi.ezsport.dtos.playerBooking.UpdatePlayerBookingRequest;
import vn.hust.hedspi.ezsport.entities.Feed;
import vn.hust.hedspi.ezsport.entities.PlayerBooking;
import vn.hust.hedspi.ezsport.entities.User;
import vn.hust.hedspi.ezsport.mappers.PlayerBookingMapper;
import vn.hust.hedspi.ezsport.repositories.FeedRepository;
import vn.hust.hedspi.ezsport.repositories.PlayerBookingRepository;
import vn.hust.hedspi.ezsport.repositories.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlayerBookingService {
    @Autowired
    PlayerBookingRepository playerBookingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FeedRepository feedRepository;

    @Autowired
    PlayerBookingMapper playerBookingMapper;

    public ApiResponse<Page<PlayerBookingResponse>> listPlayerBooking(Pageable pageable) {
        ApiResponse response = new ApiResponse();
        Page<PlayerBooking> playerBookingPage = playerBookingRepository.findAll(pageable);
        Page<PlayerBookingResponse> playerBookingResponsePage = playerBookingPage.map(playerBookingMapper::toPlayerBookingResponse);
        response.setMessage("Get list player booking successful !");
        response.setResult(playerBookingResponsePage);

        return response;
    }

    public ApiResponse<PlayerBookingResponse> createPlayerBooking(CreatePlayerBookingRequest request) {
        User bookingPlayer = userRepository.findById(request.getBookingPlayerId()).orElse(null);
        if (bookingPlayer == null) {
            ApiResponse response = new ApiResponse();
            response.setCode(404);
            response.setMessage("Booking player not found !");

            return response;
        }
        User bookedPlayer = userRepository.findById(request.getBookedPlayerId()).orElse(null);
        if (bookedPlayer == null) {
            ApiResponse response = new ApiResponse();
            response.setCode(404);
            response.setMessage("Booked player not found !");

            return response;
        } else if (bookedPlayer.getId().equals(bookingPlayer.getId())) {
            ApiResponse response = new ApiResponse();
            response.setCode(400);
            response.setMessage("Booking player and booked player must be different !");

            return response;
        }
        Feed feed = feedRepository.findById(request.getFeedId()).orElse(null);
        if (feed == null) {
            ApiResponse response = new ApiResponse();
            response.setCode(404);
            response.setMessage("Feed not found !");

            return response;
        }
        PlayerBooking playerBooking = playerBookingMapper.toCreatePlayerBookingRequest(request);
        playerBooking.setBookingUser(bookingPlayer);
        playerBooking.setBookedUser(bookedPlayer);
        playerBooking.setFeed(feed);
        PlayerBookingResponse playerBookingResponse = playerBookingMapper.toPlayerBookingResponse(playerBookingRepository.save(playerBooking));
        ApiResponse response = new ApiResponse();
        response.setMessage("Create player booking successful !");
        response.setResult(playerBookingResponse);

        return response;
    }

    public ApiResponse<PlayerBookingResponse> getPlayerBookingById(String id) {
        PlayerBooking playerBooking = playerBookingRepository.findById(id).orElse(null);
        PlayerBookingResponse playerBookingResponse = playerBookingMapper.toPlayerBookingResponse(playerBooking);
        ApiResponse response = new ApiResponse();
        response.setMessage("Get player booking successful !");
        response.setResult(playerBookingResponse);

        return response;
    }

    public ApiResponse<PlayerBookingResponse> updatePlayerBooking(String id, UpdatePlayerBookingRequest request) {
        PlayerBooking playerBooking = playerBookingRepository.findById(id).orElse(null);
        if (playerBooking == null) {
            ApiResponse response = new ApiResponse();
            response.setCode(404);
            response.setMessage("Player booking not found !");

            return response;
        }
        User bookingPlayer = userRepository.findById(request.getBookingPlayerId()).orElse(null);
        if (bookingPlayer == null) {
            ApiResponse response = new ApiResponse();
            response.setCode(404);
            response.setMessage("Booking player not found !");

            return response;
        }
        User bookedPlayer = userRepository.findById(request.getBookedPlayerId()).orElse(null);
        if (bookedPlayer == null) {
            ApiResponse response = new ApiResponse();
            response.setCode(404);
            response.setMessage("Booked player not found !");

            return response;
        } else if (bookedPlayer.getId().equals(bookingPlayer.getId())) {
            ApiResponse response = new ApiResponse();
            response.setCode(400);
            response.setMessage("Booking player and booked player must be different !");

            return response;
        }
        Feed feed = feedRepository.findById(request.getFeedId()).orElse(null);
        if (feed == null) {
            ApiResponse response = new ApiResponse();
            response.setCode(404);
            response.setMessage("Feed not found !");

            return response;
        }
        playerBooking.setBookingUser(bookingPlayer);
        playerBooking.setBookedUser(bookedPlayer);
        playerBooking.setFeed(feed);
        playerBooking.setStatus(request.getStatus());
        PlayerBookingResponse playerBookingResponse = playerBookingMapper.toPlayerBookingResponse(playerBookingRepository.save(playerBooking));
        ApiResponse response = new ApiResponse();
        response.setMessage("Update player booking successful !");
        response.setResult(playerBookingResponse);

        return response;
    }

    public ApiResponse<Void> deletePlayerBooking(String id) {
        PlayerBooking playerBooking = playerBookingRepository.findById(id).orElse(null);
        if (playerBooking == null) {
            ApiResponse response = new ApiResponse();
            response.setCode(404);
            response.setMessage("Player booking not found !");

            return response;
        }
        playerBookingRepository.delete(playerBooking);
        ApiResponse response = new ApiResponse();
        response.setMessage("Delete player booking successful !");

        return response;
    }
}
