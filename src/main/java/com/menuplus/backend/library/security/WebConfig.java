package com.menuplus.backend.library.security;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

  private final long MAX_AGE_SECS = 3600;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
      .addMapping("/**")
      .allowedOriginPatterns("*")
      //                .allowedOrigins("*")
      .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
      .allowedHeaders("*")
      .allowCredentials(true)
      .maxAge(MAX_AGE_SECS);
  }

  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer conf) {
    conf.defaultContentType(MediaType.APPLICATION_JSON);
  }

  @Override
  public void extendMessageConverters(
    List<HttpMessageConverter<?>> converters
  ) {
    for (HttpMessageConverter<?> converter : converters) {
      if (
        converter instanceof
        MappingJackson2HttpMessageConverter jsonMessageConverter
      ) {
        jsonMessageConverter
          .getObjectMapper()
          .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
          .configure(
            SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS,
            false
          )
          .configure(
            DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS,
            false
          )
          .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)
          .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        break;
      }
    }
  }
}
