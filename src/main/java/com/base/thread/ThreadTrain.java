package com.base.thread;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadTrain {



    public static Object lock1 = new Object();
    public static Object lock2 = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(){
            @Override
            public void run() {
                log.info("线程：{} :开始执行",Thread.currentThread().getName());
                try {
                    synchronized (lock1){
                        log.info("线程：{} :得到lock1锁",Thread.currentThread().getName());
                        synchronized (lock2){
                            log.info("线程：{} :得到lock2锁",Thread.currentThread().getName());
                            lock2.wait();
                            //lock.wait();
                            log.info("线程：{} :释放锁",Thread.currentThread().getName());
                       }
                    }
                    log.info("线程中断状态：{}",Thread.currentThread().isInterrupted());
                } catch (InterruptedException e) {
                    log.info("线程被中断");

                    throw new RuntimeException(e);
                }

            }
        };


        Thread threadB = new Thread(){
            @Override
            public void run() {
                log.info("线程：{} :开始执行",Thread.currentThread().getName());
                try {
                    sleep(1000);
                    synchronized (lock2){
                        log.info("线程：{} :得到lock2锁",Thread.currentThread().getName());
                        synchronized (lock1){
                            log.info("线程：{} :得到lock1锁",Thread.currentThread().getName());
                            lock1.wait();
                            //lock.wait();
                            log.info("线程：{} :释放锁",Thread.currentThread().getName());
                        }
                    }
                    log.info("线程中断状态：{}",Thread.currentThread().isInterrupted());
                } catch (InterruptedException e) {
                    log.info("线程被中断");

                    throw new RuntimeException(e);
                }

            }
        };



        threadA.setName("A");

        threadA.start();
        threadB.setName("B");
        threadB.start();
        //sleep()
        //threadB.start();
        //threadA.interrupt();
        //log.info("线程A中断状态：{}",threadA.isInterrupted());
    }
}
