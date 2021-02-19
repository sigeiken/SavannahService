package com.savannahinformatics.SavannahService;

import com.savannahinformatics.SavannahService.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
public class SavannahServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SavannahServiceApplication.class, args);
	}

}
