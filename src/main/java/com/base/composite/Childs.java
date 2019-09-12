package com.base.composite;

public class Childs extends Parents {

    private int count;

    @Override
    public void add(int i) {
        count++;
        super.add(i);
    }

    @Override
    public void addAll(int... args) {
        count+=args.length;
        super.addAll(args);
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        Childs childs = new Childs();
        childs.addAll(5,6,7);
        int count = childs.getCount();
    }
}
