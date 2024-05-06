package com.train.swordoffer;


import java.util.LinkedList;

/**
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 */
public class CQueue {

    LinkedList<Integer> list;


    public CQueue() {
        list = new LinkedList();
    }

    public void appendTail(int value) {
        list.addLast(value);
    }

    public int deleteHead() {
        if(list.isEmpty()){
            return -1;
        }
        return list.removeFirst();
    }
}
