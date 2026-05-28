package com.lab5;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Аннотация для автоматического внедрения зависимостей.
 * Помеченные ею поля будут инициализированы классом Injector
 * на основе настроек из файла config.properties.
 * 
 * @author Митюшин Максим
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoInjectable {
}