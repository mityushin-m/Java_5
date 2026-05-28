package com.lab5;


/**
 * Вспомогательный файл, содержащий интерфейсы и их реализации
 * для демонстрации работы внедрения зависимостей.
 */

/**
 * Базовый интерфейс для демонстрации.
 */
interface SomeInterface {
    void doSomething();
}

/**
 * Второй интерфейс для демонстрации множественного внедрения.
 */
interface SomeOtherInterface {
    void doSomeOther();
}

/**
 * Реализация SomeInterface, печатающая "A".
 */
class SomeImpl implements SomeInterface {
    @Override
    public void doSomething() {
        System.out.print("A");
    }
}

/**
 * Альтернативная реализация SomeInterface, печатающая "B".
 */
class OtherImpl implements SomeInterface {
    @Override
    public void doSomething() {
        System.out.print("B");
    }
}

/**
 * Реализация SomeOtherInterface, печатающая "C".
 */
class SODoer implements SomeOtherInterface {
    @Override
    public void doSomeOther() {
        System.out.print("C");
    }
}