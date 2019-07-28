package com.navent.api.orders.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private static final String CONTROLLERS_PACKAGE = "com.navent.api.orders.controller.impl";

    @Bean
    public Docket api() {
        return new Docket(SWAGGER_2)
                .groupName("navent")
                .select()
                .apis(basePackage(CONTROLLERS_PACKAGE))
                .paths(any())
                .build()
                .consumes(getAllConsumeContentTypes())
                .produces(getAllProduceContentTypes())
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API Orders")
                .description("An Orders CRUD API.")
                .build();
    }

    private Set<String> getAllConsumeContentTypes() {
        Set<String> consumes = new HashSet<>();
        // Add other media types if required in future
        consumes.add("application/json");
        return consumes;
    }

    private Set<String> getAllProduceContentTypes() {
        Set<String> produces = new HashSet<>();
        // Add other media types if required in future
        produces.add("application/json");
        return produces;
    }
}
