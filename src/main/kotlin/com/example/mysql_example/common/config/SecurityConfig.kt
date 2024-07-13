package com.example.mysql_example.common.config

import com.example.mysql_example.common.authority.JwtTokenProvider
import com.example.mysql_example.common.authority.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtTokenProvider: JwtTokenProvider
) {
    @Bean
    fun filterChain(http : HttpSecurity) : SecurityFilterChain {
        http
            .csrf {it.disable()}
            .cors {it.disable()}
            .httpBasic {it.disable()}
            .sessionManagement {
                it.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            }
            .authorizeHttpRequests{
                it.requestMatchers("/api/member/join", "/api/member/login", "/api/member/refresh").anonymous()
                    .requestMatchers("/api/**").hasRole("MEMBER")
                    .anyRequest().permitAll()
            }
            .addFilterBefore(
                JwtAuthenticationFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter::class.java
            )
        return http.build()
    }

    @Bean
    fun passwordEncorder() : PasswordEncoder =
        PasswordEncoderFactories.createDelegatingPasswordEncoder()
}
