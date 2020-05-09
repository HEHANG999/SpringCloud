package com.project;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

/**
 * 演示放入队列
 */
public class QueueIn extends Thread{


    /*//普通队列
    Queue<String> queue = null;

    //本类构造方法传值
    public QueueIn(Queue<String> queue){
        this.queue = queue;
    }

    //队列启动方法
    public void run() {
        for (int i = 0; i < 10; i++) {
            queue.offer(Thread.currentThread().getName()+"这里放入:"+i);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/




    //阻塞队列
    BlockingQueue<String> queue = null;

    //本类构造方法传值
    public QueueIn(BlockingQueue<String> queue){
        this.queue = queue;
    }

    //队列启动方法
    public void run(){
        for (int i = 0; i < 10; i++) {

            try {
                queue.put(Thread.currentThread().getName()+"/放："+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }










}
