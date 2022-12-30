package com.example.mybookstor.config.security;


import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
public class JwtAuth extends OncePerRequestFilter
{
    private final JwtUtils jwtUtils;
    private final CustomUserDetailedService customUserDetailedService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        final String authorizationHeader = request.getHeader(AUTHORIZATION);
        final String userEmail;
        final String jwtToken;


        if(authorizationHeader ==null || !authorizationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);

            return;
        }

        jwtToken = authorizationHeader.substring(7);

        userEmail = jwtUtils.getUsername(jwtToken);
        if(userEmail !=null && SecurityContextHolder.getContext().getAuthentication() ==null)
        {
            UserDetails userDetails = customUserDetailedService.loadUserByUsername(userEmail);

            if (jwtUtils.isValid(jwtToken,userDetails))
            {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);


            }
        }
        filterChain.doFilter(request,response);


    }

    }

