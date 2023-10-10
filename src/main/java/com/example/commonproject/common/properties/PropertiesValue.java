package com.example.commonproject.common.properties;

public class PropertiesValue {
    public static final String username = Properties.getProperty("admin.login.username");
    public static final String password = Properties.getProperty("admin.login.password");
    public static final String bucketLink = Properties.getProperty("firebase.bucket");
}
