package com.bajno.damian.project.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("task")
public class TaskConfigurationProperties {

    private Template template;

    public TaskConfigurationProperties(Template template) {
        this.template = template;
    }

    @Configuration
    @ConfigurationProperties("template")
    public static class Template {

        private boolean allowMultipleTask;

        public boolean isAllowMultipleTask() {
            return allowMultipleTask;
        }

        public void setAllowMultipleTask(boolean allowMultipleTask) {
            this.allowMultipleTask = allowMultipleTask;
        }
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }
}


