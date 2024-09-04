package vn.hust.hedspi.ezsport.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import vn.hust.hedspi.ezsport.dtos.ApiResponse;
import vn.hust.hedspi.ezsport.dtos.sport.CreateSportRequest;
import vn.hust.hedspi.ezsport.dtos.sport.SportResponse;
import vn.hust.hedspi.ezsport.dtos.sport.UpdateSportRequest;
import vn.hust.hedspi.ezsport.services.SportService;

@RestController
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("api/v1/sport")
public class SportController {
    @Autowired
    private SportService sportService;

    // Create
    @PostMapping()
    public ApiResponse<SportResponse> createSport(@Valid @RequestBody CreateSportRequest requestBody) {
        return sportService.createSport(requestBody);
    }

    // List Sport
    @GetMapping()
    public ApiResponse<Page<SportResponse>> listSport(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "1000") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        return sportService.listSport(pageable);
    }

    // Show
    @GetMapping("/{id}")
    public ApiResponse<SportResponse> getSportById(@PathVariable Integer id) {
        return sportService.getSportById(id);
    }

    // Update
    @PutMapping("/{id}")
    public ApiResponse<SportResponse> updateSport(@PathVariable Integer id, @Valid @RequestBody() UpdateSportRequest requestBody) {
        return sportService.updateSport(id, requestBody);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteSport(@PathVariable Integer id) {
        return sportService.deleteSport(id);
    }
}
