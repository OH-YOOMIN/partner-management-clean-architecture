package com.oynee.portfolio.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!prod")
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI partnerManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Partner Management API")
                        .description("샘플 제휴 관리 시스템 API 문서")
                        .version("v1.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Repository")
                        .url("https://github.com/OH-YOOMIN/partner-management-clean-architecture"));
    }
}