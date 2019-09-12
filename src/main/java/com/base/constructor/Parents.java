package com.base.constructor;

public class Parents {
    protected int age;
    private String name;
    {
        System.out.println("Parents property init");
    }

    public Parents() {
        // TODO Auto-generated constructor stub
        age = 100;
        name = "parents";
        System.out.println("Parents no parem constructor init");
    }
}
