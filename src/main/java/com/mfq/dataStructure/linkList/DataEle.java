package com.mfq.dataStructure.linkList;

public class DataEle {

    private Object data;

    private int cur;

    public DataEle() {
    }

    public DataEle(Object data, int cur) {
        this.data = data;
        this.cur = cur;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCur() {
        return cur;
    }

    public void setCur(int cur) {
        this.cur = cur;
    }
}
