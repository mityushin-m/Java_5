package com.lab5;

interface SomeInterface {
    void doSomething();
}

interface SomeOtherInterface {
    void doSomeOther();
}

class SomeImpl implements SomeInterface {
    @Override
    public void doSomething() {
        System.out.print("A");
    }
}

class OtherImpl implements SomeInterface {
    @Override
    public void doSomething() {
        System.out.print("B");
    }
}

class SODoer implements SomeOtherInterface {
    @Override
    public void doSomeOther() {
        System.out.print("C");
    }
}