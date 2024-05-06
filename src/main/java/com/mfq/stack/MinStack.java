package com.mfq.stack;


import java.util.Stack;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * 实现 MinStack 类:
 *
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 *
 *
 * 方法一：
 * 对于每一个节点，维护一个属性，记录此时栈底到该元素的最小值
 *
 * 方法二：核心思路，在最小值发生改变时，进行旧最小值的记录
 * 1、压栈过程中，如果最小值发生改变(相等也标注发生改变)，则压入旧的最小值与对应元素，最小值没发生改变，则仅压入对应元素
 * 2、出栈过程中，如果出栈元素等于最小值（代表最小值发生过改变），则弹出一个值为最小值（此值为旧最小值）
 * 如果等于最小值，则代表最小值未发生改变，弹出元素
 *
 * 方法三：
 * 1、以第一个元素为基点，记录所有元素与旧min值的差值；（会丢失原始值）
 *
 * 方法四：
 * 1、两个堆栈解决，一个记录原始值，一个记录最小值
 *
 * 下文中以方法二作为示例，能满足较多场景，且存储空间相对较为友好；
 */
public class MinStack {

    Stack<Integer> elems;

    int minValue = 0;

    public MinStack() {
        //初始化大小
        elems = new Stack<Integer>();
    }

    public void push(int val) {
        if(elems.isEmpty()){
            minValue = val;
        }
        if(val <= minValue){//
            elems.push(minValue);
            minValue = val;
        }
        elems.push(val);
    }

    public void pop() {
        if(elems.pop().intValue() == minValue){
            minValue = elems.pop();
        }
    }

    public int top() {
        return elems.peek();
    }

    public int getMin() {
        return minValue;
    }

    public static void main(String[] args) {

        MinStack stack = new MinStack();
        stack.push(0);
        stack.push(1);
        stack.push(0);

        System.out.println(stack.getMin());
        stack.pop();
        stack.top();
        System.out.println(stack.getMin());


    }


}
