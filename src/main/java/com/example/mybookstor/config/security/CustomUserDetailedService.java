package com.example.mybookstor.config.security;

import com.example.mybookstor.entities.MyUserDetails;
import com.example.mybookstor.entities.UserEntity;
import com.example.mybookstor.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailedService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUserDetails myUserDetails;
        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found!!!"));
        myUserDetails = new MyUserDetails(user);
        return myUserDetails;
    }
}
