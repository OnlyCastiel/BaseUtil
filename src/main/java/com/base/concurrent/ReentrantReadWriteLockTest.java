package com.base.concurrent;

import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * 读写锁
 * 读读不互斥、读写互斥、写写互斥，适用于都多写少的场景。
 * 数据库访问用的就是这种锁 X锁/S锁
 *
 * 要实现这样一个类，需要实现以下几点：
 * 1、用怎么样的标识，标记是否有其他线程持有写锁---写锁只能有一个线程持有-需要一个标志位
 * 2、用怎么样的标识，标记是否有其他线程持有读锁---读锁可以有很多线程持有-需要多个标志位
 */
public class ReentrantReadWriteLockTest {


    public static void main(String[] args) {

        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

        writeLock.lock();
        readLock.lock();

        writeLock.unlock();
        readLock.unlock();





    }

}
