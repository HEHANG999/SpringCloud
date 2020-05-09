package com.project;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;

/**
 * 演示取出队列
 */
public class QueueOut extends Thread{


    /*//普通队列
    Queue<String> queue = null;

    //本类构造方法传值
    public QueueOut(Queue<String> queue){
        this.queue = queue;
    }

    //队列启动方法
    public void run(){
        while (true){
            System.out.println(Thread.currentThread().getName()+"/取："+queue.poll());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/






    //持有阻塞队列
    BlockingQueue<String> queue = null;

    //本类构造方法传值
    public QueueOut(BlockingQueue<String> queue){
        this.queue = queue;
    }

    //队列启动方法
    public void run(){
        while (true){

            try {
                System.out.println(Thread.currentThread().getName()+"/取："+queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }








}
