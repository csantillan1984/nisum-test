package com.nisum.user.app.infrastructure.input.adapter.rest.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "jwt", ignoreUnknownFields = false)
public class JwtProperties {

    String header;
    String bearerPrefix;
    String keySecret;
    String authorities;
    Long expirationTime;
    String authority;
    String id;

}
