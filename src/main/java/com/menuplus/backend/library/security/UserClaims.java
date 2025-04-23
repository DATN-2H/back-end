package com.menuplus.backend.library.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserClaims {

  private Long userId;

  private String email;

  private Date iat;

  private Date exp;

  @JsonIgnore
  public Map<String, Object> getClaims() {
    Map<String, Object> claims = new HashMap<>();
    claims.put("userId", userId);
    claims.put("iat", iat);
    claims.put("exp", exp);
    return claims;
  }
}
