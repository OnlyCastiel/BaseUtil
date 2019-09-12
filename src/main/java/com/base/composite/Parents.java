package com.base.composite;

public class Parents {
    protected int age;

    public void add(int i){
        age +=i;
    }

    public void addAll(int... args){
        for (int i : args){
            add(i);
        }
    }
}