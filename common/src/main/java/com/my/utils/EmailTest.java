package com.my.utils;

import java.io.*;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailTest {
    
    public static void main(String[] args)  throws AddressException,MessagingException,IOException {
        EmailTest emailTest = new EmailTest();
        //String redHtml = emailTest.readHTML();
        
        Properties properties = new Properties();
        properties.put("mail.transport.protocol","smtp");  //电子邮件协议包括 SMTP，POP3，IMAP  创建和发送只需要用到 SMTP协议
        //properties.put("mail.smtp.host","casarray.systex.com.cn");  //主机名
        properties.put("mail.smtp.host","smtp.qq.com");
//        properties.put("mail.smtp.host","smtp.qiye.163.com");
        properties.put("mail.smtp.port", 465);// 端口号 QQ465
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
        //properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
        
        //获得session对象
        Session session = Session.getInstance(properties);
        //获取邮件对象
        Message message = new MimeMessage(session);
        //设置发件人邮箱地址  z.ningjianglong@systex.com.cn
        // message.setFrom(new InternetAddress("z.ningjianglong@systex.com.cn"));
        message.setFrom(new InternetAddress("494766750@qq.com"));
//        message.setFrom(new InternetAddress("xxb@sbszipper.com"));
        
        //收件人邮箱地址    数组发送多人
        //message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress("15037429431@163.com")});
        message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress("540876503@qq.com")});
        //邮件标题
        message.setSubject("java测试邮件发送111");

        String htmlText = "<head>\n" +
                "<base target=\"_blank\" />\n" +
                "<style type=\"text/css\">\n" +
                "::-webkit-scrollbar{ display: none; }\n" +
                "</style>\n" +
                "<style id=\"cloudAttachStyle\" type=\"text/css\">\n" +
                "#divNeteaseBigAttach, #divNeteaseBigAttach_bak{display:none;}\n" +
                "</style>\n" +
                "<style id=\"blockquoteStyle\" type=\"text/css\">blockquote{display:none;}</style>\n" +
                "</head>\n" +
                "<body tabindex=\"0\" role=\"listitem\">\n" +
                "\n" +
                "\n" +
                "\n" +
                "<p class=\"MsoNormal\"><span lang=\"EN-US\"><o:p>&nbsp;</o:p></span></p><p class=\"MsoNormal\"><span lang=\"EN-US\"><o:p>&nbsp;</o:p></span></p><table class=\"MsoNormalTable\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"919\" style=\"width:689.15pt;margin-left:-44.15pt;border-collapse:collapse\"><tr style=\"height:70.0pt\"><td width=\"220\" style=\"width:164.65pt;border:none;border-right:dashed windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;height:70.0pt\"><p class=\"MsoNormal\" align=\"right\" style=\"text-align:right;text-indent:22.9pt\"><span lang=\"EN-US\" style=\"color:windowtext\"><img width=\"150\" height=\"65\" style=\"width:1.5625in;height:.6805in\" id=\"_x0000_i1026\" src=\"http://mail.163.com/js6/s?func=mbox:getMessageData&amp;sid=VBrvXOWPcFLlzjhXoiPPnGPStsPpTGDN&amp;mid=462:xtbBzhCg6lQHDbvZQQAAsp&amp;part=3\"></span><span lang=\"EN-US\" style=\"color:#1F497D;mso-fareast-language:ZH-TW\"><o:p></o:p></span></p></td><td width=\"529\" valign=\"bottom\" style=\"width:14.0cm;padding:0cm 5.4pt 0cm 5.4pt;height:70.0pt\"><p class=\"MsoNormal\" align=\"left\" style=\"text-align:left;line-height:80%\"><span lang=\"EN-US\" style=\"font-size:9.0pt;line-height:80%;font-family:&quot;微软雅黑&quot;,sans-serif\">Annie Pan<o:p></o:p></span></p><p class=\"MsoNormal\" align=\"left\" style=\"text-align:left;line-height:80%\"><span lang=\"EN-US\" style=\"font-size:9.0pt;line-height:80%;font-family:&quot;微软雅黑&quot;,sans-serif\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<o:p></o:p></span></p><p class=\"MsoNormal\" align=\"left\" style=\"text-align:left;line-height:80%\"><span style=\"font-size:9.0pt;line-height:80%;font-family:&quot;微软雅黑&quot;,sans-serif\">行业应用处<span lang=\"EN-US\">&nbsp;&nbsp;</span></span><span lang=\"EN-US\" style=\"font-size:9.0pt;line-height:80%;font-family:&quot;微软雅黑&quot;,sans-serif;color:#1F3864\"><o:p></o:p></span></p><p class=\"MsoNormal\" align=\"left\" style=\"text-align:left;line-height:80%\"><span style=\"font-size:9.0pt;line-height:80%;font-family:&quot;微软雅黑&quot;,sans-serif\">精诚（中国）企业管理有限公司<span lang=\"EN-US\">&nbsp;| </span>精诚恒逸软件有限公司<span lang=\"EN-US\"> | &nbsp;</span>精诚至开（上海）信息技术有限公司<span lang=\"EN-US\"><o:p></o:p></span></span></p><p class=\"MsoNormal\" align=\"left\" style=\"text-align:left;mso-line-height-alt:8.25pt\"><span style=\"font-size:9.0pt;font-family:&quot;微软雅黑&quot;,sans-serif\">精至采购供应链软件</span><span lang=\"EN-US\" style=\"font-size:9.0pt;font-family:&quot;微软雅黑&quot;,sans-serif\">SCM//</span><span style=\"font-size:9.0pt;font-family:&quot;微软雅黑&quot;,sans-serif\">精至供应商管理软件</span><span lang=\"EN-US\" style=\"font-size:9.0pt;font-family:&quot;微软雅黑&quot;,sans-serif\">SRM//</span><span style=\"font-size:9.0pt;font-family:&quot;微软雅黑&quot;,sans-serif\">精至厂内智能物流条码软件</span><span lang=\"EN-US\" style=\"font-size:9.0pt;font-family:&quot;微软雅黑&quot;,sans-serif\">BAR<o:p></o:p></span></p><p class=\"MsoNormal\" align=\"left\" style=\"text-align:left;mso-line-height-alt:8.25pt\"><span style=\"font-size:9.0pt;font-family:&quot;微软雅黑&quot;,sans-serif\">精至智能电子看板软件</span><span lang=\"EN-US\" style=\"font-size:9.0pt;font-family:&quot;微软雅黑&quot;,sans-serif\">EKB//</span><span style=\"font-size:9.0pt;font-family:&quot;微软雅黑&quot;,sans-serif\">精至商业智能分析软件</span><span lang=\"EN-US\" style=\"font-size:9.0pt;font-family:&quot;微软雅黑&quot;,sans-serif\">BI//</span><span style=\"font-size:9.0pt;font-family:&quot;微软雅黑&quot;,sans-serif\">精至客户关系管理软件</span><span lang=\"EN-US\" style=\"font-size:9.0pt;font-family:&quot;微软雅黑&quot;,sans-serif\">CRM</span><span lang=\"EN-US\" style=\"font-size:9.0pt;font-family:&quot;微软雅黑&quot;,sans-serif\"> </span><span lang=\"EN-US\" style=\"font-size:11.0pt\"><o:p></o:p></span></p><p class=\"MsoNormal\" align=\"left\" style=\"text-align:left;line-height:80%\"><span style=\"font-size:9.0pt;line-height:80%;font-family:&quot;微软雅黑&quot;,sans-serif\">地址：上海市徐汇区漕溪北路<span lang=\"EN-US\">396</span>号汇智大厦裙楼<span lang=\"EN-US\">8</span>楼 （<span lang=\"EN-US\">200030</span>）<span lang=\"EN-US\"> &nbsp;</span>网页：<span lang=\"EN-US\">www.ucom.net.cn&nbsp; <o:p></o:p></span></span></p><p class=\"MsoNormal\" align=\"left\" style=\"text-align:left;line-height:80%\"><span style=\"font-size:9.0pt;line-height:80%;font-family:&quot;微软雅黑&quot;,sans-serif\">手机：<span lang=\"EN-US\">+86 13818187056&nbsp; | &nbsp;</span>电话：<span lang=\"EN-US\">021-33688777-395&nbsp; |&nbsp; </span>传真：<span lang=\"EN-US\">021-33688787-1290139&nbsp; <o:p></o:p></span></span></p></td><td width=\"170\" valign=\"bottom\" style=\"width:127.6pt;padding:0cm 0cm 0cm 0cm;height:70.0pt\"><p class=\"MsoNormal\" align=\"left\" style=\"text-align:left;line-height:80%\"><span lang=\"EN-US\" style=\"font-size:9.0pt;line-height:80%;font-family:&quot;微软雅黑&quot;,sans-serif\"><img width=\"97\" height=\"96\" style=\"width:1.0069in;height:1.0in\" id=\"图片_x0020_1\" src=\"http://mail.163.com/js6/s?func=mbox:getMessageData&amp;sid=VBrvXOWPcFLlzjhXoiPPnGPStsPpTGDN&amp;mid=462:xtbBzhCg6lQHDbvZQQAAsp&amp;part=4\" alt=\"cid:image006.png@01D1AF8B.F91A9980\"><o:p></o:p></span></p></td></tr></table><p class=\"MsoNormal\"><span lang=\"EN-US\"><o:p>&nbsp;</o:p></span></p><p class=\"MsoNormal\"><span lang=\"EN-US\"><o:p>&nbsp;</o:p></span></p></div> \n" +
                "\n" +
                "<style type=\"text/css\">\n" +
                "body{font-size:14px;font-family:arial,verdana,sans-serif;line-height:;padding:0;margin:0;overflow:auto;white-space:normal;word-wrap:break-word;min-height:100px}\n" +
                "td, input, button, select, body{font-family:Helvetica, 'Microsoft Yahei', verdana}\n" +
                "pre {white-space:pre-wrap;white-space:-moz-pre-wrap;white-space:-pre-wrap;white-space:-o-pre-wrap;word-wrap:break-word;width:95%}\n" +
                "th,td{font-family:arial,verdana,sans-serif;line-height:1.666}\n" +
                "img{ border:0}\n" +
                "header,footer,section,aside,article,nav,hgroup,figure,figcaption{display:block}\n" +
                "blockquote{margin-right:0px}\n" +
                "</style>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "<style id=\"ntes_link_color\" type=\"text/css\">a,td a{color:#064977}</style>\n" +
                "\n" +
                "\n" +
                "<script type=\"text/javascript\">\n" +
                "window.r_a_h=function(){return [{'id':'3','filename':'_','inlined':true},{'id':'4','filename':'_','inlined':true}];};\n" +
                "</script>\n" +
                "\n" +
                "</body>";
         message.setContent(htmlText, "text/html;charset=UTF-8");//利用html塞头和尾
//        message.setContent("", "text/html;charset=UTF-8");//利用html塞头和尾
        //邮件内容
        message.setText("测试邮件发送");
        
        //邮差对象
        Transport transport = session.getTransport();
        //连接自己的账户
        transport.connect("494766750@qq.com", "gjlalqxtclzhbgbb");// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
        //发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        
        transport.close();
    }
    
    public static String readHTML() throws IOException {
        //String spath ="C:/Users/jindongzp/Desktop/accountActive/accountActive.html";
        String spath ="C:\\Users\\Administrator\\Desktop\\html.html";
        InputStreamReader isReader = null;
        BufferedReader bufReader = null;
        StringBuffer buf = new StringBuffer();
        try {
            File file = new File(spath);
            isReader = new InputStreamReader(new FileInputStream(file), "utf-8");
            bufReader = new BufferedReader(isReader, 1);
            String data;
            while((data = bufReader.readLine())!= null) {
                
                buf.append(data);
            }
            
        } catch (Exception e) {
            //TODO 处理异常
        } finally {
            //TODO 关闭流
            isReader.close();
            
            bufReader.close();
            
        }
        //   System.out.print(buf.toString());
        return buf.toString();
    }
}