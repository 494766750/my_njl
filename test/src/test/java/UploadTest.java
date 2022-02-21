
import my.upload.UploadServer;
import my.upload.client.UploadClient;
import org.junit.jupiter.api.Test;


/**
 * 2022/2/21
 * NJL
 */

public class UploadTest {
    
    /**
     * 启动服务端
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("开始测试(服务端)");
        new Thread(new UploadServer(8099,20)).start();
        System.out.println("end/:-)");
    }
    
    /**
     * 启动客户端(单元测试启动)
     */
    @Test
    public void startClient() {
        System.out.println("开始测试(客户端)");
        new UploadClient("F:\\vx\\WeChat Files\\wxid_itxjadm1sbtn51\\FileStorage\\File\\2022-02\\CESOFT_XMLJ20211208.bak","127.0.0.1",8099).execute();
        System.out.println("end/:-)");
    }
}
