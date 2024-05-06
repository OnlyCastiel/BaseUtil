package com.base.thread;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 妈妈（生产者）生产食物装到盘子里、儿子（消费者）、女儿（消费者）拿走盘子吃盘子里面的食物；
 * 在该用例中，盘子是并发的关键，没有盘子，生产者无法将食物放入盘中，消费者也无法吃到食物；
 * 在没有食物的时候，儿子/女儿需要等待生产者生产食物，此时需要放下盘子（释放锁），回房间做别的事情去了（等待）；
 * 生产者，获取盘子（拿到锁），放入盘中（完成生产），然后将盘子放入餐桌（释放锁），通知回房间的孩子（等待唤醒）来吃；
 * 孩子被唤醒，获取装有食物的盘子（获取锁），吃完后，返回盘子（释放锁）；
 */

@Slf4j
public class MomAndSonThread {


    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);//默认非公平锁,次数设置为公平锁

        Dish dish = new Dish(0, lock);

        MomThread mon = new MomThread(dish);
        mon.setName("Mom");
        SonThread son1 = new SonThread(dish);
        son1.setName("sonA");
        SonThread son2 = new SonThread(dish);
        son2.setName("sonB");
        son1.start();
        son2.start();
        mon.start();
    }


}


//妈妈（生产者）生产食物装到盘子里、儿子（消费者）、女儿（消费者）拿走盘子吃盘子里面的食物；
@Slf4j
class MomThread extends Thread {

    private Dish dish;

    public MomThread(Dish dish) {
        this.dish = dish;
    }

    @Override
    public void run() {
        try {
            while (true) {
                dish.getLock().lock();
                //拿到盘子
                int i = new Random().nextInt(5) + 1;
                log.info("妈妈拿到盘子，开始生产食物:{}份,耗时10S", i);
                sleep(10000);
                dish.setFoodNum(i);
                log.info("妈妈生产完食物，放回盘子，喊孩子们来吃");
                dish.son.signalAll();//先唤醒其他线程-son
                dish.mom.await();//释放锁，进入无限期waiting
            }
        } catch (InterruptedException e) { //异常捕获应当在which外部，这样当异常出现是，自动推出循环，或者手动在catch中通过break退出循环
            dish.getLock().unlock();
            throw new RuntimeException(e);
        }
    }
}

@Slf4j
class SonThread extends Thread {

    private Dish dish;

    public SonThread(Dish dish) {
        this.dish = dish;
    }

    @Override
    public void run() {
        try {
            while (true) {
                sleep(new Random().nextInt(1000));
                dish.getLock().lock();
                log.info("儿子:{}拿到盘子", Thread.currentThread().getName());
                if (dish.getFoodNum() == 0) {
                    log.info("儿子:{}发现食物被吃完了，", Thread.currentThread().getName());
                    dish.getMom().signal();//此处应该指定唤醒mom,唤醒其他消费者没有意义；
                    log.info("儿子:{}放回盘子通知妈妈制作新食物", Thread.currentThread().getName());
                    dish.getSon().await(); //等待在son这个条件上，等待被唤醒；
                    continue; //避免产生超卖问题，被唤醒后需要重新上锁
                }
                //拿到盘子
                sleep(5000);
                dish.setFoodNum(dish.getFoodNum() - 1);
                log.info("儿子:{}吃了一份食物，放回盘子并通知其他人可以吃了", Thread.currentThread().getName());
                dish.getSon().signal();//通知其他人可以吃了
                dish.getLock().unlock();
            }
        } catch (InterruptedException e) {
            dish.getLock().unlock();
            throw new RuntimeException(e);
        }
    }
}

@Data
class Dish {
    private Integer foodNum;

    private ReentrantLock lock;

    Condition mom;
    Condition son;

    public Dish(Integer foodNum, ReentrantLock lock) {
        this.foodNum = foodNum;
        this.lock = lock;
        mom = lock.newCondition();
        son = lock.newCondition();
    }
}
