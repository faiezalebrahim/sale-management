package com.example.challenge2.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
        return new OpenAPI()
                .components(new Components()
                )
                .info(new Info()
                        .title("SALES SYSTEM")
                        .version(appVersion)
                        .contact(new Contact()
                                .name("800Storage")
                                .email("faiezalebrahim@gmail.com")
                                .url("https://800storagedubai.com/")
                        )
                );
    }
}
