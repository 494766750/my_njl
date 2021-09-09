package com.my;

import com.alibaba.fastjson.JSON;
import com.my.utils.StringUtils;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
                System.out.println("url：：：：：：：：：：：" + url);
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
//    public static byte[] getPicByte(String url) throws Exception {
//        if (StringUtils.isBlank(url)) {
//            return null;
//        }
//        try {
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
//            HttpGet httpGet = new HttpGet(url);
//            CloseableHttpResponse response = httpClient.execute(httpGet);
//            int statusCode = response.getStatusLine().getStatusCode();
//            if (statusCode != 200) {
//                httpGet.abort();
//                throw new RuntimeException("HttpClient,error status code :" + statusCode);
//            }
//            HttpEntity entity = response.getEntity();
//            byte[] result = null;
//            if (entity != null) {
//                result = EntityUtils.toByteArray(entity);
//            }
//            EntityUtils.consume(entity);
//            response.close();
//            return result;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

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

    public static void main(String[] args) throws Exception {


        String getByCode = "https://api.weixin.qq.com/sns/jscode2session";
//        String getByCode = "https://qyapi.weixin.qq.com/cgi-bin/user/get";
        Map<String, String> map3 = new HashMap<String, String>();
        map3.put("appid", "wxfd927fd8428a52a0");
        map3.put("js_code", "061b4mll22AyE74q6Hkl2cQR4h0b4mlc");
        map3.put("secret","83eb3c66668457e87414e9033fdd84f6");
        map3.put("grant_type","authorization_code");
        String httpResult3 = HttpClientTool.doGet(getByCode, map3, HttpClientTool.CHARSET);
        System.out.println(httpResult3);
//        doPostTestThree();
    }


    public static void doPostTestThree() {

        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("https://qyapi.weixin.qq.com/cgi-bin/kf/customer/batchget?access_token=86sOsvhcKrSnxrohCvdnk4VwM_r23MHnLK8XLpoqdGy4Wyko8id_oC3v28T0j9lR7Gn3toKPoe7er0Nf5XRODpxlK_2KoIojn5b9htglM929Fpd5Kyw6ps_dh1Hs-v0vXKQ_rz5fqyCd0LolmDBtEMCp6q_e6_vmhRKGlHO3HrY-Q9NQpZ6HBXupCemGiK20giIgl8u80-7jca_phmHlUw");
        // 创建参数
        List<String> external_userid_list = new ArrayList<>();
        external_userid_list.add("wovyc-CQAAvf93eH0H_KAQBmTcsasN6w");
        Map<String,Object> map = new HashMap<>();
//        map.put("access_token","NOwGidNApYi14t4qubL9RMahbV-sUXYobMlbT1cO0YLHBe1tFeoqG-HIAVZMftE0Hz_Q37St-7lKEMxjBOUXwp5egCXDgtDTgtXKW7a0XrpTQGnNZHZaI0hXBCLVNSFz-4L1GdCVqHm-WLcJaNle_9pqM6e3JB9pW-OSlLrvdWb4OOG9ArE4D09xg32x7kUNSeYI13ykx6qv2t38D2OqAg");
        map.put("external_userid_list",external_userid_list);
        // 创建Post请求
        // 参数
//        URI uri = null;
//        try {
//            // 将参数放入键值对类NameValuePair中,再放入集合中
//            List<NameValuePair> params = new ArrayList<>();
//            params.add(new BasicNameValuePair("access_token", "NOwGidNApYi14t4qubL9RMahbV-sUXYobMlbT1cO0YLHBe1tFeoqG-HIAVZMftE0Hz_Q37St-7lKEMxjBOUXwp5egCXDgtDTgtXKW7a0XrpTQGnNZHZaI0hXBCLVNSFz-4L1GdCVqHm-WLcJaNle_9pqM6e3JB9pW-OSlLrvdWb4OOG9ArE4D09xg32x7kUNSeYI13ykx6qv2t38D2OqAg"));
//
//            // 设置uri信息,并将参数集合放入uri;
//            // 注:这里也支持一个键值对一个键值对地往里面放setParameter(String key, String value)
//            uri = new URIBuilder().setScheme("https").setHost("qyapi.weixin.qq.com")
//                    .setPath("/cgi-bin/kf/customer/batchget").setParameters(params).build();
//        } catch (URISyntaxException e1) {
//            e1.printStackTrace();
//        }
//        HttpPost httpPost = new HttpPost(uri);



        // 将user对象转换为json字符串，并放入entity中
        StringEntity entity = new StringEntity(JSON.toJSONString(map), "UTF-8");

        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);

        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            String result = null;
            result = EntityUtils.toString(responseEntity, "utf-8");
            EntityUtils.consume(responseEntity);
            System.out.println("结果:" + result);
            System.out.println("响应状态为:" + response.getStatusLine());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
