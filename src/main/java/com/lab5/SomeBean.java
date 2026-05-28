package com.lab5;

/**
 * Класс-пример, демонстрирующий автоматическое внедрение зависимостей.
 * Поля, помеченные аннотацией {@link AutoInjectable}, будут инициализированы
 * классом {@link Injector} на основе конфигурации.
 * 
 * @author Митюшин Максим
 */
public class SomeBean {
	@AutoInjectable
    private SomeInterface field1;
    
    @AutoInjectable
    private SomeOtherInterface field2;
    
    /**
     * Вызывает методы обоих внедрённых компонентов.
     * Результат зависит от того, какие реализации были подставлены.
     */
    public void foo() {
        field1.doSomething();
        field2.doSomeOther();
    }
}
