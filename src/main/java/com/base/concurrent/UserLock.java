package com.base.concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;


/**
 * 模拟一个独占所的视线
 * 开发者只需要关注锁状态的维护，而不用关注AQS中同步等待队列的入队，出队以及释放锁后唤醒等待线程的操作
 * 而定义好锁的状态，就可以形成不同的锁，独占锁（可冲入锁）、共享锁(信号量)、以及更复杂的锁
 */
public class UserLock extends AbstractQueuedSynchronizer {


    public UserLock(){
        setState(0);
    }


    //阻塞式加锁,如何实现加锁失败后阻塞的？
    public void lock(){
        acquire(1);
    }

    public void unLock(){
        release(1);
    }

    @Override
    protected boolean tryAcquire(int arg) {
        if(getExclusiveOwnerThread() == Thread.currentThread()){
            return true;//可重入
        }
        if(compareAndSetState(0,1)){
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
        if(getExclusiveOwnerThread() == Thread.currentThread()){
            setExclusiveOwnerThread(null);
            return compareAndSetState(1,0);
        }
        return false;
    }

    @Override
    protected boolean isHeldExclusively() {
        if(getState() == 1 && Thread.currentThread() == getExclusiveOwnerThread())
            return true;
        return false;
    }

    public boolean isLocked(){
        return getState() ==1;
    }



    public static void main(String[] args) {

        UserLock userLock = new UserLock();
        for(int i = 0 ;i < 5 ; i++){//5个线程争抢
            new Thread("线程" + i + "号"){
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + "等待获取锁");
                        userLock.lock();
                        System.out.println(Thread.currentThread().getName() + "获取锁，开始执行");

                        Thread.sleep(1000);//模拟互斥
                        System.out.println(Thread.currentThread().getName() + "释放锁，执行完毕");
                        userLock.unLock();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }.start();
        }
    }
}
