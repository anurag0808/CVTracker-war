package com.kushwaha.Covid19Tracker.util;

import java.io.IOException;
import java.util.Properties;

public enum ApplicationProperties {

	INSTANCE;

    private final Properties properties;

    ApplicationProperties() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
            System.out.println(properties);
            System.out.println("ENUM::::"+ properties.stringPropertyNames());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAppName() {
    	
    	   	
        return properties.getProperty("app.name");
        
    }

	public Properties getProperties() {
		return properties;
	}
	
}
