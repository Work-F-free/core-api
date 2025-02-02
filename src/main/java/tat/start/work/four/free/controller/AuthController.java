package tat.start.work.four.free.controller;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tat.start.work.four.free.dto.SignInRequest;
import tat.start.work.four.free.dto.SignInResponse;

@RestController("/auth")
public class AuthController {

    @PostMapping("sign-in")
    public ResponseEntity<SignInResponse> fakeSignIn(@RequestBody SignInRequest request) {
        if ("admin".equals(request.login()) && "admin".equals(request.password())) {
            return ResponseEntity.ok(new SignInResponse("admin"));
        } else {
            throw new HttpUnauthorizedException("Unauthorized");
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class HttpUnauthorizedException extends RuntimeException {
        private final String message;
    }
}
