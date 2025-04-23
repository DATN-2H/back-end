package com.menuplus.backend.library.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.menuplus.backend.library.configuration.AuthProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

  @Autowired
  AuthProperties authProperties;

  @Autowired
  ObjectMapper objectMapper;

  public String generateToken(UserDetailsCustom userDetails) {
    Date now = new Date();
    Date expireDate = new Date(now.getTime() + authProperties.getJwtExpire());
    UserClaims userClaims = new UserClaims();
    userClaims.setUserId(userDetails.getUserId());
    userClaims.setIat(now);
    userClaims.setExp(expireDate);

    Key key = Keys.hmacShaKeyFor(
      authProperties.getJwtSecret().getBytes(StandardCharsets.UTF_8)
    );

    return Jwts.builder()
      .claims(userClaims.getClaims())
      .issuedAt(now)
      .expiration(expireDate)
      .signWith(key)
      .compact();
  }

  public UserClaims getUserClaimsFromToken(String token) {
    SecretKey key = Keys.hmacShaKeyFor(
      authProperties.getJwtSecret().getBytes(StandardCharsets.UTF_8)
    );

    Jws<Claims> jws = Jwts.parser()
      .verifyWith(key)
      .build()
      .parseSignedClaims(token);

    Claims claims = jws.getPayload();

    return objectMapper.convertValue(claims, UserClaims.class);
  }
}
