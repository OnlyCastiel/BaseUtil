package com.base.constructor;

public class Childs extends Parents{

    private String points;

    {
        System.out.println("childs property init");
        System.out.println("childs age:"+age);
    }

    public Childs(String points) {
        // TODO Auto-generated constructor stub
        this.points = points;
        System.out.println("Childs constructor init");
    }

    public Childs(Integer points) {
        new Childs("100");
        // TODO Auto-generated constructor stub
        System.out.println("Childs constructor 100");
    }



    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Parents child = new Childs(100);
    }
}
