package com.base.concurrent;


import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 本目录主要整理jdk中并发相关的工具类，增加额外注释便于理解
 *
 * 并发的三大特性：可见性、原子性、有序性
 * JVM的内存模型：程序执行是如何访问内存中的数据的；
 *
 * volatile:保证可见性，通过设置内存屏障，确保每次访问数据都是访问主存
 *
 * synchronized : 保证原子性，通过jvm层面在对象head部的monitor，记录锁标志位设置值来标识是否被锁占用，同时记录了一个指针，指向等待该锁的线程队列；(操作系统层面实现，涉及内核态切换，更消耗性能)
 *
 * CAS: compare and swap ；unsafe提供的原子性操作，比较并替换，操作一个 volatile int state 变量，实现原子性的加锁操作；
 *
 * AQS （AbstractQueuedSynchronizer）:
 * 同步等待队列模型，实现原子式同步状态管理、阻塞和唤醒功能以及队列模型
 * 通过unsafe直接访问主存中的一个volatile修饰的的标志位，来判定锁的状态；
 * 通过unsafe直接访问对
 * jdk层面实现的锁的等待队列（与synchronized机制同一个思路不同的实现）
 *
 *
 * ReentrantLock（可重入锁）:独占锁  volatile int state =  0/1;
 * Semaphore （信号量）：共享锁  volatile int state =  n;  (n>0)
 * CountDownLatch：（倒计时门闩）： volatile int state =  1/n
 *
 *
 *
 * 问题：加锁是通过CAS实现原子性，保证线程安全的，那么如果加锁失败，线程需要进入同步等待队列时，如何保证保证并发安全；
 * 加锁成功或失败是通过CAS实现的
 * 加锁失败时，将线程放入同步等待队列也是通过CAS实现的，通过记录tail的位置(AQS初始化时固定)，在插入时判断tail位置是否为之前的node;
 */
public class aReadme {

    private static final long stateOffset = 0;
    public static void main(String[] args) {

    }

}
