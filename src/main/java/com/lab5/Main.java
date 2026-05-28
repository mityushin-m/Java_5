package com.lab5;

/**
 * Демонстрационный класс, запускающий пример внедрения зависимостей.
 * 
 * @author Митюшин Максим
 */
public class Main {
	/**
     * Точка входа в программу.
     * Создаёт экземпляр {@link SomeBean}, внедряет зависимости через
     * {@link Injector} и вызывает метод {@link SomeBean#foo()}.
     *
     * 
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {

        SomeBean sb = new SomeBean();

        Injector injector = new Injector();
        injector.inject(sb);

        sb.foo(); // выведет "AC" или "BC" в зависимости от config.properties
    }
}
