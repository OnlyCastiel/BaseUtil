package com.base.concurrent;

import java.util.concurrent.CountDownLatch;


/**
 * 倒计时门闩
 * 使用场景：多等一、一等多
 * 某一个或者一组线程 await;等待另一个或一组线程 countDown() 至0, 当为 state = 0时，await阻塞的线程会被唤醒；
 * 同时后续线程 await不会进入阻塞；
 */
public class CountDownLatchTest {

    private static CountDownLatch countDownLatch = new CountDownLatch(5);

    private static int N = 50;

    /**
     * 经典范例
     * 司机到达，工人开始干活 ；多等一
     * 工人干完活，司机启动； 一等多
     */
    class Driver { // ...
        void main() throws InterruptedException {
            CountDownLatch startSignal = new CountDownLatch(1);
            CountDownLatch doneSignal = new CountDownLatch(N);

            for (int i = 0; i < N; ++i) // create and start threads
                new Thread(new Worker(startSignal, doneSignal)).start();

            //doSomethingElse();            // don't let run yet
            startSignal.countDown();      // let all threads proceed
            // doSomethingElse();
            doneSignal.await();           // wait for all to finish
        }
    }

    class Worker implements Runnable {
        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;

        Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }

        public void run() {
            try {
                startSignal.await();
                doWork();
                doneSignal.countDown();
            } catch (InterruptedException ex) {
            } // return;
        }

        void doWork() {
        }
    }
}
