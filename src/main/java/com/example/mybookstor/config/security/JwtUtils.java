package com.example.mybookstor.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class JwtUtils
{

    @Value("${JWTkey}")
    private String secretKey;

    public String getUsername(String token){
        return extractClaims(token, Claims::getSubject);
    }

    public Date getExpirationDate(String token){
        return extractClaims(token, Claims::getExpiration);
    }

    public boolean isExpired(String token){
        return getExpirationDate(token).before(new Date());
    }

    public boolean hasClaim(String token, String claimName){
         final Claims claims = extractAllClaims(token);

         return claims.get(claimName)!=null;
    }

    public <T> T extractClaims(String token, Function<Claims,T> clamsReolver){
        final Claims claims = extractAllClaims(token);
        return clamsReolver.apply(claims);


    }
    public Claims extractAllClaims(String token){
        return  Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }



    public String generateToken(UserDetails userDetails ){
        Map<String, Object> claims = new HashMap<>();

        return createToken(claims, userDetails);

    }
    public String createToken(Map<String, Object> claims, UserDetails userDetails){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .claim("authorities", userDetails.getAuthorities())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24)))
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }
    public String createToken(Authentication auth){
        return Jwts.builder()
//                .setClaims()
                .setSubject(auth.getName())
                .claim("authorities", auth.getAuthorities())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24)))
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
//
    }


    public  boolean isValid(String token, UserDetails userDetails){
        String username = getUsername(token);

        return (username.equals(userDetails.getUsername())&& !isExpired(token));
    }
    public Set<SimpleGrantedAuthority> getSimpleGrantedAuthorities(String token){
        Claims body = extractAllClaims(token);
        var authorities = (List<Map<String,Object >>) body.get("authorities");

        return  authorities.stream()
                .map(m -> new SimpleGrantedAuthority( m.get("authority").toString()))
                .collect(Collectors.toSet());
    }

}


////
////        Authentication authentication = new UsernamePasswordAuthenticationToken(
////                username,
////                null,
////                simpleGrantedAuthoritySet


