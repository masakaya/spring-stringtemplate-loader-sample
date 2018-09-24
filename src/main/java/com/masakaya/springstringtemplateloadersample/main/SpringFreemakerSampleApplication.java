package com.masakaya.springstringtemplateloadersample.main;

import com.masakaya.springstringtemplateloadersample.configure.FreemakerConfigure;
import com.masakaya.springstringtemplateloadersample.utility.FreemakerTmplateUtils;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Import;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Import(FreemakerConfigure.class)
@ComponentScan({"com.masakaya.springstringtemplateloadersample.utility"})
@SpringBootApplication
public class SpringFreemakerSampleApplication implements ApplicationRunner {

	@Autowired
	FreemakerTmplateUtils utils;

	@Override
	public void run(ApplicationArguments args) {
		String templateId = "TMP_001";
		String templateMessage = "my name is ${name}";
		Map<String , Object> dataMap = new HashMap<>();
		dataMap.put("name" , "Masashi Kayahara");
		try {
			String output = utils.bind(templateId, templateMessage ,dataMap );
			log.info(output);
			log.info("process end.");
		} catch (TemplateException e) {
			e.printStackTrace();
		}


	}

	public static void main(String[] args) {
		SpringApplication.run(SpringFreemakerSampleApplication.class, args);
	}
}
