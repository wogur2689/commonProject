package com.example.commonproject.common.properties;

import jakarta.annotation.Resource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

@Repository
public class Properties {
    static Environment environment;

    @Resource
    public void setEnvironment(Environment env) {
        environment = env;
    }

    public static String getProperty(String key) {
        return environment.getProperty(key);
    }
}
