package com.project;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    public static void main(String[] args) {

        //普通队列
        //Queue<String> queue = new LinkedList<String>();//这是父队列

        //创建阻塞式队列
        BlockingQueue queue = new ArrayBlockingQueue(10);//这个长度由队列内容决定

        //创建非阻塞式队列
        //ConcurrentLinkedQueue queue = new ArrayBlockingQueue<String>(10);//与上面阻塞队列效果一样


        //创建添加线程和获取线程
        QueueIn push1 = new QueueIn(queue);
        QueueIn push2 = new QueueIn(queue);

        QueueOut pop1 = new QueueOut(queue);
        QueueOut pop2 = new QueueOut(queue);

        push1.start();
        push2.start();
        pop1.start();
        pop2.start();

    }
}
