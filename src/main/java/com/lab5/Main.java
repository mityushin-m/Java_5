package com.lab5;

public class Main {
    public static void main(String[] args) {

        SomeBean sb = new SomeBean();

        Injector injector = new Injector();
        injector.inject(sb);

        sb.foo();
    }
}
