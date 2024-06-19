package com.example.mysql_example.common.config

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@SecurityScheme(name = "BearerAuth", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")

@Configuration
class SwaggerConfig {

    @Bean
    fun openApi() : OpenAPI = OpenAPI()
        .components(Components())
        .info(apiInfo())

    private fun apiInfo() : Info = Info()
        .title("게시판 서버 API 명세서")
        .description("동아리 스터디에서 만든 게시판 서버의 API 명세서입니다. 회원가입과 게시판 서비스를 제공하고 있습니다.")
        .version("1.0.0")
}