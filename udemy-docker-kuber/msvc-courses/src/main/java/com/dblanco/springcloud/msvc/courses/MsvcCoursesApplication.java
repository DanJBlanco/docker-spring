package com.dblanco.springcloud.msvc.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcCoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcCoursesApplication.class, args);
	}

}
