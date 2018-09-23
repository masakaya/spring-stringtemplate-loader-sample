package com.masakaya.springstringtemplateloadersample.configure;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.context.annotation.Bean;
import freemarker.template.Configuration;

import java.nio.charset.StandardCharsets;

@org.springframework.context.annotation.Configuration
public class FreemakerConfigure {

    @Bean
    public StringTemplateLoader stringTemplateLoader() {
       return new StringTemplateLoader();
    }

    @Bean
    public Configuration freeMakerConfiguration( StringTemplateLoader stringLoader ) {
        Configuration configuration = new Configuration(
                Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding(StandardCharsets.UTF_8.name());
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setLogTemplateExceptions(false);
        configuration.setWrapUncheckedExceptions(true);
        configuration.setTemplateLoader(stringLoader);
        return configuration;
    }
}
