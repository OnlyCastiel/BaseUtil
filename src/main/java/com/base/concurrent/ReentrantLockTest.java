package com.base.concurrent;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * ReentrantLock 是多个机制组合最终实现的线程并发控制；包含以下内容：
 * ● 独占锁，仅允许一个线程独占
 * ● CAS （unsafe）：加锁解锁的具体实现机制
 * ● AQS  (queue ) ：同步等待队列，排队等待获取锁，内部维护volatile  int state 作为锁标志
 * ● Condition (await/signal/signalall) ：条件等待队列，等待/唤醒，重新获取锁
 */
public class ReentrantLockTest {


    //锁
    private final ReentrantLock lock = new ReentrantLock();

    //缓冲区
    private final Queue<Integer> buffer = new LinkedList<>();

    //最大容量
    private final Integer capacity = 10;

    //未满时条件-信号量，用于唤醒等待此信号的线程
    private final Condition notFull = lock.newCondition();

    //缓冲区满时条件
    private final Condition notEmpty = lock.newCondition();


    //生产者
    public void product(){
        try {
            lock.lock();//阻塞式加锁
            //判断是否可以生产
            if (buffer.size() == capacity) { //并发场景下可能会超出capacity
                System.out.println(Thread.currentThread().getName() + "缓存满，await");
                notFull.await();//释放锁，并退出cpu调度等待被唤醒,被唤醒时，会重新尝试获取锁，否则阻塞
                System.out.println(Thread.currentThread().getName() + "可生产，被唤醒" + lock.getHoldCount());//猜想，被唤醒后是否持有锁
                if(buffer.size() >= capacity){//被唤醒后，再次判断缓存区
                    return;
                }
            }
            buffer.add(1);
            System.out.println(Thread.currentThread().getName() + "product:" + buffer.size());
            notEmpty.signalAll();//唤醒所有消费者
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + "生产完毕，释放锁");
        }
    }


    public void consumer(){
        try {
            lock.lock();
            if(buffer.isEmpty()){
                System.out.println(Thread.currentThread().getName() + "缓存空，await");
                notEmpty.await();
                System.out.println(Thread.currentThread().getName() + "可消费，被唤醒" + lock.getHoldCount());
            }
            this.wait();
            buffer.poll();
            System.out.println(Thread.currentThread().getName() + "consumer:" + buffer.size());
            notFull.signalAll();
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + "消费完毕，释放锁");
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest lockTest = new ReentrantLockTest();

        //两个生产者
        for (int i = 0 ; i < 2 ; i++){
            new Thread("product{" + i+ "}"){
                @Override
                public void run() {
                    while (true){
                        lockTest.product();
                    }
                }
            }.start();
        }

        //两个消费者
        for (int i = 0 ; i < 2 ; i++){
            new Thread("consumer{" + i+ "}"){
                @Override
                public void run() {
                    while (true){
                        lockTest.consumer();
                    }
                }
            }.start();
        }

    }
}
