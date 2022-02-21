package my.upload;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @标题 Socket服务
 * @作者 gdl
 *
 */

public class UploadServer extends Thread{
    
    
    /**
     * 上传端口
     */
    private int socketPort ;
    
    /**
     * 线程池数量
     * (不是单个CPU线程池大小,是总数)
     */
    private int poolSize ;
    
    /**
     * Socket服务
     */
    private ServerSocket serverSocket ;
    
    private boolean running = true;
    
    /**
     * 线程池
     */
    private ExecutorService executorService;
    
    public UploadServer(int socketPort,int poolSize) {
        this.socketPort = socketPort;
        this.poolSize = poolSize;
        try {
            serverSocket = new ServerSocket(socketPort);
            executorService =Executors.newFixedThreadPool(poolSize);
        }catch(Exception e) {
        
        }
        
    }
    
    public void run() {
        
        while(running) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                executorService.execute(new UploadHandler(socket));
            }catch(Exception e) {
            
            }
            try {
                
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 停止服务
     */
    public void Stop() {
        this.running = false;
    }
}