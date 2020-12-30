package com.designmodel.factory;

public class SimpleFactoryTest {

    public Draw getInstance(String name) {
        if (name == null) {
            throw new NullPointerException("name can not be null");
        }
        if ("CIRCLE".equals(name)) {
            return new Circle();
        } else if ("SQUARE".equals(name)) {
            return new Square();
        } else {
            throw new IllegalArgumentException();
        }
    }

}
