package com.taskboard.taskboardbackend.Service;

import com.taskboard.taskboardbackend.DTO.LoginRequest;
import com.taskboard.taskboardbackend.DTO.LoginResponse;
import com.taskboard.taskboardbackend.DTO.RegisterRequest;
import com.taskboard.taskboardbackend.DTO.RegisterResponse;
import com.taskboard.taskboardbackend.Exception.InvalidCredentialsException;
import com.taskboard.taskboardbackend.Exception.UserAlreadyExistsException;
import com.taskboard.taskboardbackend.Model.Users;
import com.taskboard.taskboardbackend.Repository.UserRepository;
import com.taskboard.taskboardbackend.Utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public RegisterResponse register(RegisterRequest request){
        if(userRepository.existsByEmail(request.email())){
            throw new UserAlreadyExistsException("A user with this email id is already present");
        }
        Users user = Users.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .email(request.email())
                .build();
        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getEmail());
        return new RegisterResponse(user.getUsername(),user.getEmail(), token);
    }

    public LoginResponse login(LoginRequest request){
        try{
            authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(request.email(),request.password()));
            String token = jwtUtil.generateToken(request.email());
            return new LoginResponse(request.email(),token);
        }
        catch (BadCredentialsException e){
            throw new InvalidCredentialsException("Invalid username or password");
        }
    }
}
