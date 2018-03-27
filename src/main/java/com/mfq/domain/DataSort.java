package com.mfq.domain;


public class DataSort implements Comparable<DataSort>{
    public int data;
    public String detail;

    public DataSort(int data, String detail) {
        super();
        this.data = data;
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "[" + data +"]";
    }

    @Override
    public int compareTo(DataSort o) {
        return this.data>o.data ? 1:(this.data<o.data ? -1:0);
    }

}