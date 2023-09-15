package com.smud.socksensespringproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    @Profile({"devswagger && !prodswagger"})  // 만약 로컬환경일경우 swagger 활성화
    public Docket restAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.shj.socksensespringproject.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    @Profile({"prodswagger && !devswagger"})  // 만약 운영환경일경우 swagger 비활성화
    public Docket disable() {
        return new Docket(DocumentationType.SWAGGER_2).enable(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SockSense REST API")
                .version("1.0.0")
                .description("SockSense의 api 문서입니다.")
                .build();
    }
}
