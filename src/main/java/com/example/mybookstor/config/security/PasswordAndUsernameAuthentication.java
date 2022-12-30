package com.example.mybookstor.config.security;

import com.example.mybookstor.response.LoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor

public class PasswordAndUsernameAuthentication extends UsernamePasswordAuthenticationFilter {

//    private final JwtUtils jwtUtils;
    private  final JwtUtils jwtUtils;

    private final AuthenticationManager manager;

//    public PasswordAndUsernameAuthentication( AuthenticationManager manager) {
//
//        this.manager = manager;
//    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException
    {
        try {
            LoginDto loginDto = new ObjectMapper()
                    .readValue(request.getInputStream(),LoginDto.class);
            Authentication authentication  = new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword());
            return manager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException

    {
      String token =  jwtUtils.createToken(authResult);

        response.addHeader(AUTHORIZATION, "Bearer "+ token );

    }




}
