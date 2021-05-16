package com.bajno.damian.project.controller;


import com.bajno.damian.project.config.TaskConfigurationProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
    private TaskConfigurationProperties taskConfigurationProperties;
    private DataSourceProperties dataSource;

    public InfoController(TaskConfigurationProperties taskConfigurationProperties, DataSourceProperties dataSource) {
        this.taskConfigurationProperties = taskConfigurationProperties;
        this.dataSource = dataSource;
    }

    @GetMapping("/info/url")
    public String displayUrl() {
        return dataSource.getUrl();
    }


    @GetMapping("/info/prop")
    public boolean isProp() {
        return taskConfigurationProperties.getTemplate().isAllowMultipleTask();
    }
}
