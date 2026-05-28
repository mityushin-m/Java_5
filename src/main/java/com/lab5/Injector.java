package com.lab5;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class Injector {
    
    private Properties properties;

    public Injector() {
        properties = new Properties();
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (in == null) {
                throw new RuntimeException("config.properties not found in resources");
            }
            properties.load(in);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load properties", e);
        }
    }
    
    public <T> T inject(T obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                String interfaceName = field.getType().getName();
                String implClassName = properties.getProperty(interfaceName);
                if (implClassName == null) {
                    throw new RuntimeException("No implementation found for " + interfaceName);
                }
                try {
                    Class<?> implClass = Class.forName(implClassName);
                    Object implInstance = implClass.getDeclaredConstructor().newInstance();
                    field.setAccessible(true);
                    field.set(obj, implInstance);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to inject field " + field.getName(), e);
                }
            }
        }
        return obj;
    }
}