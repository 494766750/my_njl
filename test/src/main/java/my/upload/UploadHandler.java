package my.upload;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.Socket;

/**
 * @title Socket处理类
 * @author gdl
 *
 */
public class UploadHandler implements Runnable{

//    private static Logger LOG = LoggerFactory.getLogger(UploadHandler.class);
    
    /**
     * Socket
     */
    private Socket socket ;
    private String dir = "E:\\aa" ;
    private int buffer_size = 1024*64 ;//每次传输文件长度
    
    private InputStream input = null;
    private OutputStream output = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;
    private RandomAccessFile raf = null;
    
    public UploadHandler(Socket socket) {
//        LOG.info("连接成功");
        this.socket = socket ;
    }
    
    /**
     * 1. 首先获取文件的md5,文件大小,文件名称
     * 2. 返回前端文件是否存在,文件大小,文件名
     *
     */
    public void run() {
        try {
            input = socket.getInputStream();
            output = socket.getOutputStream();
            in = new DataInputStream(input);
            out = new DataOutputStream(output);
            
            //第一次交互
            int len = in.readInt();
            byte[] buffer = new byte[len];
            in.read(buffer);
            String msg = new String(buffer,UploadConstant.CHARSET);
            System.out.println("MSG:"+msg);
            String[] strs = msg.split("\\|");
            String filemd5 = strs[0] ;
            long filelength = Long.valueOf(strs[1]);
            String filename = strs[2];
            
            msg = analyse(filemd5, filelength, filename);
            
            buffer = msg.getBytes(UploadConstant.CHARSET);
            out.writeInt(buffer.length);
            out.write(buffer);
            out.flush();
            
            
            //第二次交互 (仅当02开头时继续上传)
            if(msg.startsWith(UploadConstant.E02)) {
                File tempfile = new File(dir,filename+".tmp");
                if(!tempfile.exists()) {
                    tempfile.createNewFile();
                }
                raf=new RandomAccessFile(tempfile, "rw");
                long offset = tempfile.length() ;
                raf.seek(offset);
                int length;
                byte[] buf=new byte[buffer_size];
                while((length=in.read(buf, 0, buf.length))!=-1){
                    raf.write(buf,0,length);
                    offset += length;
                    //响应上传进度
                    if(offset == filelength) {
                        //上传结束
                        break;
                    }
                }
                try {
                    close(raf);
                    raf = null;
                }finally {
                    msg = response(filemd5, filename, tempfile);
                    
                    buffer = msg.getBytes(UploadConstant.CHARSET);
                    out.writeInt(buffer.length);
                    out.write(buffer);
                    out.flush();
                }
            }
        } catch(Exception e) {
             e.getMessage();
        } finally {
            close(raf);
            close();
        }
        
    }
    
    /**
     * 上传结束后响应报文
     * @param filemd5
     * @param filename
     * @param tempfile
     * @return
     */
    private String response(String filemd5, String filename, File tempfile) {
        String msg;
        String md5 = MD5Util.getMD5(tempfile);
        if(md5.equalsIgnoreCase(filemd5)) {
            File target = new File(dir,filename);
             System.out.println("开始重命名");
            tempfile.renameTo(target);//重命名
            if(target.exists()) {
//                LOG.info("重命名成功");
                msg = UploadConstant.E06;
            }else {
//                LOG.info("重命名失败");
                msg = UploadConstant.E04;
            }
            
        }else {
             System.out.println("上传成功,但是MD5和上传前不同");
            msg = UploadConstant.E05;
        }
        return msg;
    }
    
    /**
     * 分析
     * @param filemd5
     * @param filelength
     * @param filename
     * @return
     *   00 文件大小为0
     *   01 文件存在,不需要重新上传
     *   02 临时文件存在,竖线后是文件大小
     *   03 文件存在,但是md5不同
     * @throws IOException
     */
    private String analyse(String filemd5, long filelength, String filename) throws IOException {
        String msg;
        //根据md5,文件名等信息获取文件是否存在
        File file = new File(dir,filename);
        if(file.exists()) {
            String md5 = MD5Util.getMD5(file);
            if(md5.equalsIgnoreCase(filemd5)) {
//                LOG.info("上传文件已经存在且MD5相同,不需要重新上传");
                msg = UploadConstant.E01 ;
            }else {
//                LOG.info("上传文件已经存在,但是MD5不同");
                msg = UploadConstant.E03 ;
            }
        }else {
            if(0 == filelength) {//上传文件大小为零
                file.createNewFile();
//                LOG.info("上传文件大小为0");
                msg = UploadConstant.E00 ;
            }else {
                file = new File(dir,filename+".tmp");//未上传完整的临时文件
                if(file.exists()) {
                    long len = file.length();
//                    LOG.info("上传文件不存在,但是临时文件存在,文件大小:"+len);
                    msg = UploadConstant.E02+"|"+buffer_size+"|"+len ;
                }else {
//                    LOG.info("上传文件不存在");
                    msg = UploadConstant.E02+"|"+buffer_size+"|0" ;
                }
            }
        }
        return msg;
    }
    
    private void close() {
        close(out);
        close(in);
        close(output);
        close(input);
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