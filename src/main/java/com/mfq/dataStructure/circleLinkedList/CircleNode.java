package com.mfq.dataStructure.circleLinkedList;

public class CircleNode {

    private Object ele;

    private CircleNode next;

    public CircleNode(Object ele, CircleNode next) {
        this.ele = ele;
        this.next = next;
    }

    public void setNext(CircleNode ele){
        this.next = ele;
    }

    public CircleNode getNext(){
        return next;
    }

    public Object getEle(){
        return ele;
    }
}
