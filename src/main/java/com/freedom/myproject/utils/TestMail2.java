package com.freedom.myproject.utils;

import java.util.Date;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

 /* qq邮箱默认SMTP/POP3服务是关闭的,其他邮箱是默认开启的。
          qq邮箱开启SMTP/POP3服务时会要求使用授权码，并在使用第三方客户端发送
          邮件时用授权码代替密码。所以使用qq邮箱的SMTP服务时，邮箱+授权码。
          使用其他邮箱的SMTP服务时，邮箱+密码：*/
@SuppressWarnings("all")
public class TestMail2 {
    private final static String myEmailAccount = "1787098791@qq.com";
    private final static String myEmailPassword = "bnqaejotpmkfedfc";
    // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
    // qq邮箱的 SMTP 服务器地址为: smtp.qq.com
    private static String myEmailSMTPHost = "smtp.qq.com";

    // 收件人邮箱（替换为自己知道的有效邮箱）
    //private   String receiveMailAccount;
    //收件人登录名
    //private  String receiveMailName;



    public static void sendMail(String receiveMailName,String receiveMailAccount,String password) {
        Properties props = new Properties(); // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");  // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);
        props.setProperty("mail.smtp.port", "25");//设置端口  // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true"); // 需要请求认证
//      props.setProperty("mail.smtp.starttls.enable", "true");            // 需要请求认证
// 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getInstance(props);
        //session.setDebug(true); // 设置为debug模式, 可以查看详细的发送 log

// 3. 创建一封邮件
        MimeMessage message = null;
        Transport transport = null;
        try {
            message = createMimeMessage(session, myEmailAccount, receiveMailAccount,receiveMailName,password);
            transport = session.getTransport();
            transport.connect(myEmailAccount, myEmailPassword);//连接两方
// 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());

// 7. 关闭连接
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static   MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,String receiveMailName,String password) throws Exception {
// 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

// 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
        message.setFrom(new InternetAddress(sendMail));

// 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail));

        // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
        message.setSubject("欢迎使用", "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
        message.setContent("<b>尊敬的"+receiveMailName+"您的密码已经重置为: "+"<span style='background-color:blue'>"+password+"</span>"+"请及时到个人中心修改密码！！！ </b>", "text/html;charset=UTF-8");

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }

}