package com.mfq.dataStructure.twoWayCircleLinkedList;

public class CircleNode {

    private Object ele;

    private CircleNode front;

    private CircleNode next;

    public CircleNode(Object ele, CircleNode front, CircleNode next) {
        this.ele = ele;
        this.front = front;
        this.next = next;
    }

    public Object getEle() {
        return ele;
    }

    public void setEle(Object ele) {
        this.ele = ele;
    }

    public CircleNode getFront() {
        return front;
    }

    public void setFront(CircleNode front) {
        this.front = front;
    }

    public CircleNode getNext() {
        return next;
    }

    public void setNext(CircleNode next) {
        this.next = next;
    }
}
