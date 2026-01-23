package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConfig {

    private final String schema;

    public DatabaseConfig(@Value("${app.database.schema}") String schema) {
        this.schema = schema;
    }

    public String getSchema() {
        return schema;
    }
}

