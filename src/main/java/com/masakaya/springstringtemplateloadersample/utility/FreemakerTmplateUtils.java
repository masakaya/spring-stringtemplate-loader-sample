package com.masakaya.springstringtemplateloadersample.utility;

import freemarker.cache.StringTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class FreemakerTmplateUtils {
    /**
     * Freemaker Configuration.
     */
    private final Configuration freemakerConfiguration;

    public String bind(final String templateId,
                       final String templateContent,
                       Map<String, Object> dataMap) throws TemplateException {
        if (hasTemplate(templateId)) {
            putTemplate(templateId, templateContent);
        }
        Template template;
        try (StringWriter out = new StringWriter()) {
            template = freemakerConfiguration.getTemplate(templateId);
            template.process(dataMap, out);
            return out.toString();
        } catch (IOException e) {
            throw new IllegalArgumentException(
                    MessageFormat.format("Template is not found. template id is {}.", templateId), e);
        } catch (TemplateException e) {
            throw e;
        }
    }

    private boolean hasTemplate(final String templateId) {
        TemplateLoader templateLoader = this.freemakerConfiguration.getTemplateLoader();
        try {
            templateLoader.findTemplateSource(templateId);
            return true;
        } catch (IOException e) {
            log.debug("template is not found. [templateId={}]", templateId);
            return false;
        }
    }

    private void putTemplate(final String templateId,
                             final String templateContent) {
        TemplateLoader templateLoader = this.freemakerConfiguration.getTemplateLoader();
        ((StringTemplateLoader) templateLoader).putTemplate(templateId, templateContent);
    }

    // TODO apache-commons or jackson or orika-mapper
    public <T> Map<String, Object> toBindDataMap(T bean) {
        return null;
    }
}
