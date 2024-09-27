package com.nisum.user.app.infrastructure.input.adapter.rest.configuration;

import com.nisum.user.app.infrastructure.input.adapter.rest.filter.AuthorizationFilter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RestSecurityConfig {

  AuthorizationFilter authorizationFilter;
  @Bean
  public SecurityFilterChain configureParams(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
    MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
    http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            request ->
                request
                    .requestMatchers( mvcMatcherBuilder.pattern("/auth/**"))
                    .permitAll()
                    .requestMatchers(mvcMatcherBuilder.pattern("/doc/**"))
                    .permitAll()
                    .requestMatchers(mvcMatcherBuilder.pattern("/swagger-ui/**"), mvcMatcherBuilder.pattern("/v3/api-docs/**"), mvcMatcherBuilder.pattern("/swagger-ui.html"))
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        .addFilterAfter(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
