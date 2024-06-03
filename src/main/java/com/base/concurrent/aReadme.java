package com.base.concurrent;


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
 * CAS: compare and swap ；
 * unsafe提供的原子性操作，比较并替换，操作一个 volatile int state 变量，实现原子性的加锁操作；
 * 此操作也可以修改其他内容以实现线程安全（加锁失败线程入队）；
 *
 * AQS （AbstractQueuedSynchronizer）:
 * 同步等待队列模型，实现原子式同步状态管理、阻塞和唤醒功能以及队列模型
 * 通过unsafe直接访问主存中的一个volatile修饰的的标志位，来判定锁的状态；
 * 通过LockSupport.park() / LockSupport.unpark() 来进行线程的休眠和唤醒；
 * 被唤醒后会重试 tryAcquire 方法进行加锁
 * jdk层面实现的锁的等待队列（与synchronized机制同一个思路不同的实现）
 *
 * Condition 条件等待队列（在AQS中定义）
 * Condition维护了一个子队列（AQS中为父队列），子队列受同一个Condition控制
 * 执行conditon.await()时，线程加入该条件队列中
 * 执行conditon.doSignal()时，唤醒条件等待队列队首线程
 * 执行conditon.doSignalAll()时，唤醒条件等待队列所有线程
 * 通过LockSupport.park() / LockSupport.unpark() 来进行线程的休眠和唤醒；
 *
 *
 * ReentrantLock（可重入锁）:独占锁  (AQS) volatile int state =  0/1;
 * Semaphore （信号量）：共享锁 (AQS) volatile int state =  n;  (n>0)
 * CountDownLatch：（倒计时门闩）：(AQS)  volatile int state =  1/n
 * CyclicBarrier (回环栅栏) ： ReentrantLock + Condition  + parties(参与人数)
 *
 *
 * 问题：
 * 1、加锁是通过CAS实现原子性，保证线程安全的，那么如果加锁失败，线程需要进入同步等待队列时，如何保证保证并发安全；
 * 加锁成功或失败是通过CAS实现的
 * 加锁失败时，将线程放入同步等待队列也是通过CAS实现的，通过记录tail的位置(AQS初始化时固定)，在插入时判断tail位置是否为之前的node;
 *
 * 2、为什么非公平锁性能比公平锁更高（吞吐量）
 * 非公平锁在尝试加锁的时候不去判断是否有其他线程等待，而是直接判断是否可以加锁，有效降低了线程状态切换的次数，从而减少了性能消耗（用户态->内核态）
 *
 *
 * 3、共享锁、独占锁的核心区别
 * AQS中定义了两个方法 protected tryAcquire() / protected tryAcquireShared()
 * 这两个方法需要由子类定义，如何通过state来进行共享判定以及独占判定的具体方法；
 * 而判定成功后，由AQS 实现了共享锁，独占锁的入队逻辑，以及对应的队列唤醒逻辑
 *
 * 4、为什么需要设计可重入的机制，是如何设计的
 * 判断当前持有锁的线程是否是自己，并将state+1
 * 4.1、为什么要在重入的时候将state+1
 * 避免出现错误释放的情况，加多少次锁，就需要释放多少次；在方法调用过程中十分重要；
 *
 *
 * 3、独占锁，和共享锁的区别？
 * 独占锁，锁对于线程是独占的；而共享锁时允许有多个线程共同持有
 *
 */
public class aReadme {

    private static ReentrantLock lock = new ReentrantLock();



}
