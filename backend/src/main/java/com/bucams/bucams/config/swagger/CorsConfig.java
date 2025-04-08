package com.bucams.bucams.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") // 모든 URL에 대해
					.allowedOrigins("http://localhost:5173") // 프론트 주소 허용
					.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 메소드
					.allowedHeaders("*")
					.allowCredentials(true); // 인증 정보 허용 (쿠키 등)
			}
		};
	}
}
