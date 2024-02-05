package io.github.paulmarcelinbejan.fabrick.feign.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.okhttp.OkHttpClient;

@Configuration
public class FabrickClientConfig {

	@Bean
	OkHttpClient client() {
		return new OkHttpClient();
	}

	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}

}