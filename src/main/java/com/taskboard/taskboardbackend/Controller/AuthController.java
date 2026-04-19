package com.taskboard.taskboardbackend.Controller;

import com.taskboard.taskboardbackend.DTO.LoginRequest;
import com.taskboard.taskboardbackend.DTO.LoginResponse;
import com.taskboard.taskboardbackend.DTO.RegisterRequest;
import com.taskboard.taskboardbackend.DTO.RegisterResponse;
import com.taskboard.taskboardbackend.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }
}
