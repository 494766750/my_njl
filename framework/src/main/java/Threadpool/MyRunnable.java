package Threadpool;

/**
 * 2022/4/22
 * NJL
 */
public class MyRunnable implements Runnable {
    private int num;
    MyRunnable(int num) {
        this.num = num;
    }
    @Override
    public void run() {
        System.out.println("正在执行任务: " + num);
    }
    public static void main(String[] args) throws InterruptedException {
        MyThreadPool myThreadPool = new MyThreadPool();
        for(int i = 0; i < 1000; i++) {
            myThreadPool.execute(new MyRunnable(i + 1));
        }
        Thread.sleep(2000);			//主线程休眠2s
        myThreadPool.shutDown();		//销毁所有工作线程
        System.out.println("线程池已经被销毁了");
    }
    
}