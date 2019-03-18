package org.nz.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.nz.bean.User;

import com.sun.mail.util.MailSSLSocketFactory;

/**
* @author 作者 : YN
* @version 创建时间：2018年12月24日 下午2:58:16
* 类说明：
*
*/
public class MailUtil implements Runnable{
	
//	private String code;// 激活码
	private User user;
	private PropertyUtil propUtil = new PropertyUtil("mail.properties");

	public MailUtil(User user) {
		
//		this.code = code;
		this.user = user;
	}

	public void run() {
		// 1.创建连接对象javax.mail.Session
		// 2.创建邮件对象 javax.mail.Message
		// 3.发送一封激活邮件
//		String from =  "zhiyou100test001@163.com"; //"XXX";// 发件人电子邮箱
//		String host = "smtp.163.com";//"XXX"; // 指定发送邮件的主机smtp.qq.com(QQ)|smtp.163.com(网易)

		Properties properties = System.getProperties();// 获取系统属性
		
//		properties.setProperty("mail.smtp.host", host);// 设置邮件服务器
		properties.setProperty("mail.smtp.host", propUtil.getProperty("email.sender.host"));// 设置邮件服务器
		properties.setProperty("mail.smtp.auth", "true");// 打开认证

		try {
			//QQ邮箱需要下面这段代码，163邮箱不需要
			MailSSLSocketFactory sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
			properties.put("mail.smtp.ssl.enable", "true");
			properties.put("mail.smtp.ssl.socketFactory", sf);
			
			
			// 1.获取默认session对象
			Session session = Session.getDefaultInstance(properties, new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					//return new PasswordAuthentication("XXX", "XXX"); // 发件人邮箱账号、授权码
//					return new PasswordAuthentication("zhiyou100test001@163.com", "Zy123456"); // 发件人邮箱账号、授权码
					return new PasswordAuthentication(propUtil.getProperty("email.sender"), propUtil.getProperty("email.auth.code")); // 发件人邮箱账号、授权码
				}
			});

			// 2.创建邮件对象
			Message message = new MimeMessage(session);
			// 2.1设置发件人
//			message.setFrom(new InternetAddress(from));
			message.setFrom(new InternetAddress(propUtil.getProperty("email.sender")));
			// 2.2设置接收人
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
			// 2.3设置邮件主题
			message.setSubject(MimeUtility.encodeText(propUtil.getProperty("email.subject"), "UTF-8", "B"));
			message.setSubject(propUtil.getProperty("email.subject"));
//			message.setSubject("账号激活");
			// 2.4设置邮件内容
			String content = propUtil.getProperty("email.content.1")
					+ user.getActivationCode() + propUtil.getProperty("email.content.2") + user.getActivationCode()
					+ propUtil.getProperty("email.content.3");
			message.setContent(content, "text/html;charset=UTF-8");
			//message.setContent(content, propUtil.getProperty("email.message.content"));
			System.out.println(message);
			// 3.发送邮件
			Transport.send(message);
			
			System.out.println("邮件成功发送!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
//		String code = new Random().nextInt(100000) + "";
		String code = CommonUtils.uuid();
		User user = new User();
		user.setEmail("15225985337@163.com");
		user.setActivationCode(code);
		MailUtil mu = new MailUtil(user);
		mu.run();
		System.out.println("=========================="+code);
		
//		PropertyUtil propUtil = new PropertyUtil("mail.properties");
//		System.out.println("======sender======" + propUtil.getProperty("email.sender"));
	}
}


