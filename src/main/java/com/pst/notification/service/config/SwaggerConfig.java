package com.pst.notification.service.config;

import java.time.LocalTime;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket apiDocket() {

    return new Docket(DocumentationType.SWAGGER_2)
        .forCodeGeneration(true)
        .apiInfo(getApiInfo())
        .directModelSubstitute(LocalTime.class, String.class)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.pst.notification.service.controller"))
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo getApiInfo() {
    return new ApiInfoBuilder().title("[Notification] Swagger API Doc").version("1.0.0").build();
  }
}
