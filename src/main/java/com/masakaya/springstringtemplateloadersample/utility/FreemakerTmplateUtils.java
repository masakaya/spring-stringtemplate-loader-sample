package com.masakaya.springstringtemplateloadersample.utility;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class FreemakerTmplateUtils {
    /**
     * Freemaker Configuration.
     */
    private final Configuration freemakerConfiguration;

    /**
     * Freemaker's String Loader.
     */
    private final StringTemplateLoader stringLoader;

    public String bind(final String templateId,
                       final String templateContent,
                       Map<String, Objects> dataMap) throws TemplateException {
        if(Objects.isNull(stringLoader.findTemplateSource(templateId)) ) {
            stringLoader.putTemplate(templateId,templateContent);
        }
        Template template;
        try(StringWriter out = new StringWriter()) {
            template = freemakerConfiguration.getTemplate(templateId);
            template.process(dataMap, out);
            return out.toString();
        } catch (IOException e) {
            throw new IllegalArgumentException(
                    MessageFormat.format("Template is not found. template id is {}.",templateId), e);
        } catch (TemplateException e) {
            throw e;
        }
    }

    // TODO apache-commons or jackson or orika-mapper
    public <T> Map<String,Object> toBindDataMap( T bean ) {
        return null;
    }
}
