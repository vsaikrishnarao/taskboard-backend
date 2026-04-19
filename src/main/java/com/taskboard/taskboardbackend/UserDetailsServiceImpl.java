package com.taskboard.taskboardbackend;

import com.taskboard.taskboardbackend.Model.Users;
import com.taskboard.taskboardbackend.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepository.findUsersByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found"));

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities("ROLE_USER")
                .build();
    }
}
