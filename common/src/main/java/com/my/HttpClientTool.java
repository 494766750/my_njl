package com.my;

import com.alibaba.fastjson.JSON;
import com.my.utils.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HttpClientTool {
    private static final CloseableHttpClient httpClient;
    public static final String CHARSET = "UTF-8";
    
    // 采用静态代码块，初始化超时时间配置，再根据配置生成默认httpClient对象
    static {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(15000).build();
        httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }
    
    public static String doGet(String url, Map<String, String> params) {
        return doGet(url, params, CHARSET);
    }
    
    public static String doGetSSL(String url, Map<String, String> params) {
        return doGetSSL(url, params, CHARSET);
    }
    
    public static String doPost(String url, Map<String, String> params) throws IOException {
        return doPost(url, params, CHARSET);
    }
    
    /**
     * HTTP Get 获取内容
     *
     * @param url     请求的url地址 ?之前的地址
     * @param params  请求的参数
     * @param charset 编码格式
     * @return 页面内容
     */
    public static String doGet(String url, Map<String, String> params, String charset) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            if (params != null && !params.isEmpty()) {
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
                // 将请求参数和url进行拼接
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
//                System.out.println("url：：：：：：：：：：：" + url);
            }
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpGet.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * HTTP Get 获取内容
     *
     * @param url 请求的url地址 ?之前的地址
     * @return 页面内容
     */
    public static byte[] getPicByte(String url) throws Exception {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
//            if (params != null && !params.isEmpty()) {
//                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
//                for (Map.Entry<String, String> entry : params.entrySet()) {
//                    String value = entry.getValue();
//                    if (value != null) {
//                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
//                    }
//                }
//                // 将请求参数和url进行拼接
//                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
//            }
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpGet.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            byte[] result = null;
            if (entity != null) {
                result = EntityUtils.toByteArray(entity);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * HTTP Post 获取内容
     *
     * @param url     请求的url地址 ?之前的地址
     * @param params  请求的参数
     * @param charset 编码格式
     * @return 页面内容
     * @throws IOException
     */
    public static String doPost(String url, Map<String, String> params, String charset)
            throws IOException {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        List<NameValuePair> pairs = null;
        if (params != null && !params.isEmpty()) {
            pairs = new ArrayList<NameValuePair>(params.size());
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String value = entry.getValue();
                if (value != null) {
                    pairs.add(new BasicNameValuePair(entry.getKey(), value));
                }
            }
        }
        HttpPost httpPost = new HttpPost(url);
        if (pairs != null && pairs.size() > 0) {
            httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
        }
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (response != null)
                response.close();
        }
        return null;
    }
    
    /**
     * HTTPS Get 获取内容
     *
     * @param url     请求的url地址 ?之前的地址
     * @param params  请求的参数
     * @param charset 编码格式
     * @return 页面内容
     */
    public static String doGetSSL(String url, Map<String, String> params, String charset) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            if (params != null && !params.isEmpty()) {
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
            }
            HttpGet httpGet = new HttpGet(url);
            
            // https  注意这里获取https内容，使用了忽略证书的方式，当然还有其他的方式来获取https内容
            CloseableHttpClient httpsClient = HttpClientTool.createSSLClientDefault();
            CloseableHttpResponse response = httpsClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpGet.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 这里创建了忽略整数验证的CloseableHttpClient对象
     *
     * @return
     */
    public static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                // 信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }
    
    public static String doTemplateMsgPost(String templateMsgUrl,String paramStr){
        String res=null;
        URL url = null;
        try {
            url = new URL(templateMsgUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            if (null != paramStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(paramStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            conn.disconnect();
            res=buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  res;
    }
    
    
    public static String doGetPost(String apiPath, String type, Map<String, Object> paramMap){
        OutputStreamWriter out = null;
        InputStream is = null;
        String result = null;
        try{
            URL url = new URL(apiPath);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod(type) ; // 设置请求方式
            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            if(type.equals("POST")){
                out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
                out.append(JSON.toJSONString(paramMap));
                out.flush();
                out.close();
            }
            // 读取响应
            is = connection.getInputStream();
            int length = (int) connection.getContentLength();// 获取长度
            if (length != -1) {
                byte[] data = new byte[length];
                byte[] temp = new byte[512];
                int readLen = 0;
                int destPos = 0;
                while ((readLen = is.read(temp)) > 0) {
                    System.arraycopy(temp, 0, data, destPos, readLen);
                    destPos += readLen;
                }
                result = new String(data, "UTF-8"); // utf-8编码
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  result;
    }
    
    
    
    
    public static void main(String[] args) throws Exception {
        String gettoken = "";
        gettoken = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("corpid", "ww1a969a6804d1e9cf");
        map.put("corpsecret", "zMmy8JT1S7VIBH_SYegmfC1xq21LRrgO5O8emSf7SKY");
        String httpResult = HttpClientTool.doGet(gettoken, map, HttpClientTool.CHARSET);
        System.out.println(httpResult);
        
        String getUserId = "https://qyapi.weixin.qq.com/cgi-bin/user/get";
//        "ea1Y0u63haczJd85EqFvSN4Wzd8-S3UyXxfJ_t4e7BmBuHDXypgRRFAcZI15G0t3dEMGjX16ujBpFC0Dy88VPDdaYrXKrm_fm7ChHTVXf2hqQZYDFp-6i9LurdxIze8i39W7L-sISk8U9izykj34cOiuXlp_XgSfHJ5YxDRKqkgx9JYa34YjyrB_JDyEjR2Zw8Fs2kajTVCwKs5aQBr1ZQ";
        
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("access_token", "ea1Y0u63haczJd85EqFvSN4Wzd8-S3UyXxfJ_t4e7BmBuHDXypgRRFAcZI15G0t3dEMGjX16ujBpFC0Dy88VPDdaYrXKrm_fm7ChHTVXf2hqQZYDFp-6i9LurdxIze8i39W7L-sISk8U9izykj34cOiuXlp_XgSfHJ5YxDRKqkgx9JYa34YjyrB_JDyEjR2Zw8Fs2kajTVCwKs5aQBr1ZQ");
        map2.put("userid", "HuangPanHong");
        String httpResult2 = HttpClientTool.doGet(getUserId, map2, HttpClientTool.CHARSET);
        System.out.println(httpResult2);
        
        String getByCode = "https://qyapi.weixin.qq.com/cgi-bin/miniprogram/jscode2session";
        Map<String, String> map3 = new HashMap<String, String>();
        map3.put("access_token", "ea1Y0u63haczJd85EqFvSN4Wzd8-S3UyXxfJ_t4e7BmBuHDXypgRRFAcZI15G0t3dEMGjX16ujBpFC0Dy88VPDdaYrXKrm_fm7ChHTVXf2hqQZYDFp-6i9LurdxIze8i39W7L-sISk8U9izykj34cOiuXlp_XgSfHJ5YxDRKqkgx9JYa34YjyrB_JDyEjR2Zw8Fs2kajTVCwKs5aQBr1ZQ");
        map3.put("js_code", "xJJj9QI-FJpEKRWLV5VLsndKyzsA_jt5gXCnZQKNG_Q");
        map3.put("grant_type","authorization_code");
        String httpResult3 = HttpClientTool.doGet(getByCode, map3, HttpClientTool.CHARSET);
        System.out.println(httpResult3);
    }
    
    
}
