package com.nisum.user.app.application.service;

import com.nisum.user.app.infrastructure.input.adapter.rest.configuration.JwtProperties;
import com.nisum.user.app.infrastructure.util.HmacUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class JwtAdapter {
  JwtProperties jwtProperties;

  public String getToken(String user) {
    List<GrantedAuthority> grantedAuthorities =
        AuthorityUtils.commaSeparatedStringToAuthorityList(jwtProperties.getAuthority());
    String token =
        Jwts.builder()
            .setId(jwtProperties.getId())
            .setSubject(user)
            .claim(
                jwtProperties.getAuthorities(),
                grantedAuthorities.stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList()))
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpirationTime()))
            .signWith(HmacUtil.getHmacKey(jwtProperties.getKeySecret()), SignatureAlgorithm.HS512)
            .compact();
    return "Bearer " + token;
  }

  public String getTokenOfHeader(String authorization) {
    return authorization.replace("Bearer ","");
  }
}
