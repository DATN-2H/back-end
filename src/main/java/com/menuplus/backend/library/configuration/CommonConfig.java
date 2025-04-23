package com.menuplus.backend.library.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
public class CommonConfig {

  @Bean
  public CharacterEncodingFilter characterEncodingFilter() {
    final CharacterEncodingFilter characterEncodingFilter =
      new CharacterEncodingFilter();
    characterEncodingFilter.setEncoding("UTF-8");
    characterEncodingFilter.setForceEncoding(true);
    return characterEncodingFilter;
  }
}
