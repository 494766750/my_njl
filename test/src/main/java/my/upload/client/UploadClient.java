package my.upload.client;



import my.upload.MD5Util;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.Socket;





/**
 * 2022/2/21
 * NJL
 */
public class UploadClient extends Thread{

//    private static Logger LOG = LoggerFactory.getLogger(UploadClient.class);
    
    
    DataInputStream in = null;
    DataOutputStream out = null;
    
    File file ;
    String ip;
    int port;
    
    public UploadClient(String filepath, String ip, int port) {
        this.file = new File(filepath);
        this.ip = ip;
        this.port = port ;
    }
    
    public UploadClient(File file, String ip, int port) {
        this.file = file;
        this.ip = ip;
        this.port = port ;
    }
    
    public static UploadClient upload(String filepath, String ip, int port) throws FileNotFoundException {
        File file = new File(filepath);
        if(!file.exists()) {
            throw new FileNotFoundException("上传文件不存在");
        }
        UploadClient instance = new UploadClient(file,ip,port) ;
        instance.start();
        return instance ;
    }
    
    public void run() {
        execute();
    }
    
    /**
     * 执行文件上传
     */
    public void execute() {
        String filename = file.getName();
        String filemd5 = MD5Util.getMD5(file);
        long filelength = file.length();
        String msg = filemd5 + "|" + filelength + "|" + filename;
        Socket socket = null;
        OutputStream output = null;
        InputStream input = null ;
        RandomAccessFile raf = null;
        try {
            socket = new Socket(ip,port);
            socket.setSoTimeout(30000);//设置超时时间:30秒
            output = socket.getOutputStream();
            input = socket.getInputStream();
            out = new DataOutputStream(output);
            in = new DataInputStream(input);
            
            //发送文本 (第一次交互)
            byte[] buffer = msg.getBytes("gbk");
            int len = buffer.length ;
            out.writeInt(len);
            out.write(buffer);
            out.flush();
            //收到响应
            len = in.readInt();
            buffer = new byte[len];
            in.read(buffer);
            msg = new String(buffer,"gbk");
             System.out.println("response : "+msg);
            /*
             * 收到报文后第二阶段
             *   00 文件大小为0
             *   01 文件存在,不需要重新上传
             *   02 临时文件存在,竖线后是文件大小
             *   03 文件存在,但是md5不同
             */
            if(msg.startsWith("02")) {
//                LOG.info("开始上传");
                String[] strs = msg.split("\\|");
                int size = Integer.valueOf(strs[1]);
                long offset = Long.valueOf(strs[2]);
                raf = new RandomAccessFile(file,"r");
                raf.seek(offset);
                int length = 0;
                byte[] buf = new byte[size];
                while((length=raf.read(buf))>0){
                    out.write(buf,0,length);
                    out.flush();
                }
                
//                LOG.info("上传结束");
                len = in.readInt();
                buffer = new byte[len];
                in.read(buffer);
                msg = new String(buffer,"gbk");
//                LOG.info("上传后响应标示:"+msg);
            }else {
                //TODO ...
//                LOG.info("未处理标示:"+msg);
            }
            
        } catch (Exception e) {
             e.getMessage();
        } finally {
            close(raf);
            close(out);
            close(in);
            close(input);
            close(output);
        }
        
        
    }
    
    private void close(Closeable stream) {
        if(null != stream) {
            try {
                stream.close();
            } catch (Exception e) {
//                LOG.error("关闭{}发生异常",stream.getClass().getSimpleName(),e);
            }
        }
    }
    
}
