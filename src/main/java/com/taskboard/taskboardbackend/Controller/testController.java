package com.taskboard.taskboardbackend.Controller;

import com.taskboard.taskboardbackend.Utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class testController {
    private final JwtUtil jwtUtil;

    @GetMapping("/{email}")
    public String testToken(@PathVariable String email){
        return jwtUtil.generateToken(email);
    }

    @GetMapping("/decode")
    public String getEmailFromJwt(@RequestParam String token){
        return jwtUtil.extractToken(token);
    }
}
