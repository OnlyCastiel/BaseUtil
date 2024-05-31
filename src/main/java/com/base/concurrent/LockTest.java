package com.base.concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class LockTest extends AbstractQueuedSynchronizer {


    public LockTest(){
        setState(0);
    }


    //阻塞式加锁,如何实现加锁失败后阻塞的？
    public void lock(){
        tryAcquire(1);
    }


    public boolean tryLock(){
        return tryAcquire(1);
    }

    @Override
    protected boolean tryAcquire(int arg) {
        if(getExclusiveOwnerThread() == Thread.currentThread()){
            return true;//可重入
        }
        if(compareAndSetState(0,1)){
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }else{

        }

        return true;
    }

    @Override
    protected boolean tryRelease(int arg) {
        return super.tryRelease(arg);
    }

    @Override
    protected int tryAcquireShared(int arg) {
        return super.tryAcquireShared(arg);
    }

    @Override
    protected boolean tryReleaseShared(int arg) {
        return super.tryReleaseShared(arg);
    }

    @Override
    protected boolean isHeldExclusively() {
        return super.isHeldExclusively();
    }
}
