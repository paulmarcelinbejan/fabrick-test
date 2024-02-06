package io.github.paulmarcelinbejan.fabrick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties
@ComponentScan({ "io.github.paulmarcelinbejan*" })
public class FabrickTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(FabrickTestApplication.class, args);
	}

}
