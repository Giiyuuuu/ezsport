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
import vn.hust.hedspi.ezsport.domain.dtos.playerbooking.CreatePlayerBookingRequest;
import vn.hust.hedspi.ezsport.domain.dtos.playerbooking.PlayerBookingResponse;
import vn.hust.hedspi.ezsport.domain.dtos.playerbooking.UpdatePlayerBookingRequest;
import vn.hust.hedspi.ezsport.services.PlayerBookingService;

@RestController
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("api/v1/player-booking")
public class PlayerBookingController {
    @Autowired
    private PlayerBookingService playerBookingService;

    //List all player booking
    @GetMapping()
    public ApiResponse<Page<PlayerBookingResponse>> listPlayerBooking(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1000") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        return playerBookingService.listPlayerBooking(pageable);
    }

    //Create
    @PostMapping()
    public ApiResponse<PlayerBookingResponse> createPlayerBooking(@Valid @RequestBody CreatePlayerBookingRequest requestBody) {
        return playerBookingService.createPlayerBooking(requestBody);
    }

    //Show
    @GetMapping("/{id}")
    public ApiResponse<PlayerBookingResponse> getPlayerBookingById(@PathVariable String id) {
        return playerBookingService.getPlayerBookingById(id);
    }

    //Update
    @PutMapping("/{id}")
    public ApiResponse<PlayerBookingResponse> updatePlayerBooking(@PathVariable String id, @Valid @RequestBody() UpdatePlayerBookingRequest requestBody) {
        return playerBookingService.updatePlayerBooking(id, requestBody);
    }

    //Delete
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deletePlayerBooking(@PathVariable String id) {
        return playerBookingService.deletePlayerBooking(id);
    }
}