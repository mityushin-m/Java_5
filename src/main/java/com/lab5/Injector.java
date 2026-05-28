package com.lab5;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Класс, реализующий внедрение зависимостей через рефлексию.
 * Читает файл {@code config.properties} и для каждого поля,
 * помеченного аннотацией {@link AutoInjectable}, создаёт экземпляр
 * указанной в конфигурации реализации и присваивает его полю.
 * 
 * @author Митюшин Максим
 */
public class Injector {
    
    private Properties properties;

    /**
     * Конструктор, загружающий конфигурацию из файла {@code config.properties}.
     * 
     * @throws RuntimeException если файл не найден или не может быть прочитан
     */
    public Injector() {
        properties = new Properties();
        // Загружаем файл
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (in == null) {
                throw new RuntimeException("config.properties not found in resources");
            }
            properties.load(in);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load properties", e);
        }
    }
    
    /**
     * Внедряет зависимости в переданный объект.
     * 
     * @param obj объект, поля которого нужно обработать
     * @param <T> тип объекта
     * @return тот же объект с инициализированными полями
     * @throws RuntimeException если для какого-либо интерфейса не найдена реализация
     *         или не удалось создать экземпляр реализации
     */
    public <T> T inject(T obj) {
    	// Получаем все поля класса
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
        	
        	// Проверяем, есть ли на поле аннотация @AutoInjectable
            if (field.isAnnotationPresent(AutoInjectable.class)) {
            	
            	// Получаем тип поля (это есть интерфейс)
                String interfaceName = field.getType().getName();
                
                // Ищем в properties, какой класс реализует этот интерфейс
                String implClassName = properties.getProperty(interfaceName);
                if (implClassName == null) {
                    throw new RuntimeException("No implementation found for " + interfaceName);
                }
                try {
                	// Загружаем класс-реализацию по имени
                    Class<?> implClass = Class.forName(implClassName);
                    
                   // Создаём экземпляр реализации
                    Object implInstance = implClass.getDeclaredConstructor().newInstance();
                    
                    // Делаем приватное поле доступным для записи
                    field.setAccessible(true);
                    // Присваиваем полю созданный экземпляр
                    field.set(obj, implInstance);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to inject field " + field.getName(), e);
                }
            }
        }
        return obj;
    }
}