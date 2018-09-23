package com.masakaya.springstringtemplateloadersample.main;

import com.masakaya.springstringtemplateloadersample.configure.FreemakerConfigure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Slf4j
@Import(FreemakerConfigure.class)
@SpringBootApplication
public class SpringFreemakerSampleApplication implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) {

	}

	public static void main(String[] args) {
		SpringApplication.run(SpringFreemakerSampleApplication.class, args);
	}
}
