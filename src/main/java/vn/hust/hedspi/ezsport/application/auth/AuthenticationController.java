package vn.hust.hedspi.ezsport.application.auth;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.hust.hedspi.ezsport.domain.dtos.ApiResponse;
import vn.hust.hedspi.ezsport.domain.dtos.auth.AuthenticationRequest;
import vn.hust.hedspi.ezsport.domain.dtos.auth.AuthenticationResponse;

@RestController()
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequestMapping("api/v1/auth")
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        var result = authenticationService.authentication(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('READ_ALL_DATA')")
    ApiResponse<String> checkAdmin(){
        return ApiResponse.<String>builder()
                .result("ADMIN")
                .build();
    }

    @GetMapping("/owner")
    @PreAuthorize("hasAuthority('OWE_FIELD')")
    ApiResponse<String> checkOwner(){
        return ApiResponse.<String>builder()
                .result("OWNER")
                .build();
    }
}
