package Threadpool;

import java.util.concurrent.BlockingQueue;

/**
 * 2022/4/22
 * NJL
 */
//实现工作线程 - 工作线程中维护了公有的任务队列（阻塞）, 工作线程的执行逻辑。 循环取队列中的任务去执行处理。
public class Worker extends Thread {
    //阻塞任务队列 - 可以保证多个线程对队列操作， 线程安全
    private BlockingQueue<Runnable> queue = null;
    //每个工作线程都会有一个阻塞队列，这个队列中保存了所有的任务
    public Worker(BlockingQueue<Runnable> queue, int id) {
        this.queue = queue;
        //   Thread.currentThread().setName("郝梦武" + id + "号工作线程");
    }
    
    //工作线程执行内容
    @Override
    public void run() {
        //每个线程通过isInterrupted()判断线程异常状态。
        try {
            while (!Thread.currentThread().isInterrupted()) {
                //如果线程正常， 返回false, 出现异常， 返回true， 该状态默认为false
                Runnable command = queue.take();    //如果队列为空， take会让线程阻塞
                System.out.println(Thread.currentThread().getName() + "正在处理任务" + command.hashCode());
                command.run();
            }
        }
        catch(InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "被中止了");
        }
    }
    
}

