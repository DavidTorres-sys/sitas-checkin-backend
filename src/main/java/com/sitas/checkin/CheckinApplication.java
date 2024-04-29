package com.sitas.checkin;

import com.sitas.checkin.domain.jpa.mapper.user.IMedicalInfoMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
@ComponentScan({
	"com.sitas.checkin.domain",
	"com.sitas.checkin.services",
	"com.sitas.checkin.controller",
	"com.sitas.checkin.utils",
})
public class CheckinApplication {
	public static void main(String[] args) {
		SpringApplication.run(CheckinApplication.class, args);
	}

	@Bean
	@Primary
	public IMedicalInfoMapper medicalInfoMapper() {
		return IMedicalInfoMapper.INSTANCE;
	}

}
