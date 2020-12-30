package com.service;

@FunctionalInterface
public interface A<T> {

    int compare(T t1, T t2);

    boolean equals(Object va1);
}
