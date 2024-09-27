package com.nisum.user.app.infrastructure.input.adapter.rest.filter;

import com.nisum.user.app.infrastructure.input.adapter.rest.configuration.JwtProperties;
import com.nisum.user.app.infrastructure.util.HmacUtil;
import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorizationFilter extends OncePerRequestFilter {
  JwtProperties jwtProperties;

  private Claims setKey(HttpServletRequest request) {
    String jwtToken =
        request.getHeader(jwtProperties.getHeader()).replace(jwtProperties.getBearerPrefix(), "");

    return Jwts.parserBuilder()
        .setSigningKey(HmacUtil.getHmacKey(jwtProperties.getKeySecret()))
        .build()
        .parseClaimsJws(jwtToken)
        .getBody();
  }

  private void setAuthentication(Claims claims) {

    List<String> authorities = (List<String>) claims.get(jwtProperties.getAuthorities());

    UsernamePasswordAuthenticationToken auth =
        new UsernamePasswordAuthenticationToken(
            claims.getSubject(),
            null,
            authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

    SecurityContextHolder.getContext().setAuthentication(auth);
  }

  private boolean isTokenValid(HttpServletRequest request, HttpServletResponse res) {
    String authenticationHeader = request.getHeader(jwtProperties.getHeader());
    if (authenticationHeader == null
        || !authenticationHeader.startsWith(jwtProperties.getBearerPrefix())) return false;
    return true;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      if (isTokenValid(request, response)) {
        Claims claims = setKey(request);
        if (claims.get(jwtProperties.getAuthorities()) != null) {
          setAuthentication(claims);
        } else {
          SecurityContextHolder.clearContext();
        }
      } else {
        SecurityContextHolder.clearContext();
      }
      filterChain.doFilter(request, response);
    } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
    }
  }

}
