package com.my.Threadpool;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 2022/4/22
 * NJL
 */
//线程池 - 维护很多个线程， 当来一个任务时， 从线程池中获取一个线程去处理执行。
//好处： 防止线程频繁开辟和销毁带来的性能损耗
@Component
public class MyThreadPool {
    //创建任务线程安全的队列, 保证多个线程对这个队列操作时是线程安全的
    private BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
    //线程管理列表 - 这个列表保存了所有线程对象的引用， 方便后续的管理
    private List<Worker> Wokers = new ArrayList<>();
    private final static int maxWorkerCount = 10;   //线程池最大允许的个数
    
    //execute方法
    public void execute(Runnable command) throws InterruptedException {
        queue.put(command);                      //添加任务到线程安全的队列中
        if(Wokers.size() < maxWorkerCount) {
            //创建一个新的工作线程
            Worker worker = new Worker(queue, Wokers.size());   //创建工作线程
            worker.start();                      //创建的工程线程启动
            Wokers.add(worker);                  //添加到管理列表中
        }
    }
    
    //销毁所有线程 - 将每个线程中状态置为中断状态方法， 并且
    public void shutDown() throws InterruptedException {
        for(Worker worker : Wokers) {
            worker.interrupt();                  //将线程的状态置为中断， 调用isInterruptd()返回值为true
        }
        //并且让主线程join阻塞等待所有工作线程
        for(Worker worker : Wokers) {
            worker.join();                       //join方法可以让调用的线程处于阻塞状态， 知道等待的线程结束完毕之后就会恢复
        }
        //执行到这块， 代表所有的线程销毁完毕
        System.out.println("所有工作线程销毁完毕!");
    }
}

