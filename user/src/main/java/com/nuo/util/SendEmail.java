package com.nuo.util;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;


public class SendEmail {

	public static String myEmailAccount = "";
	public static String myEmailPassword = "";
	public static String myEmailSMTPHost = "smtp.exmail.qq.com";
	public static String receiveMailAccount = "";

	public static void main(String[] args) throws Exception {
		// 1. 创建参数配置, 用于连接邮件服务器的参数配置
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");// 使用的协议（JavaMail规范要求）
		properties.setProperty("mail.smtp.host", myEmailSMTPHost); // 发件人的邮箱的 SMTP 服务器地址
		properties.setProperty("mail.smtp.auth", "true");// 需要请求认证

		// SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
		// QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
		final String smtpPort = "465";
		properties.setProperty("mail.smtp.port", smtpPort);
		properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLServerSocketFactory");
		properties.setProperty("mail.smtp.socketFactory.fallback", "false");
		properties.setProperty("mail.smtp.socketFactory.port", smtpPort);
		properties.setProperty("mail.smtp.ssl.enable", "true");
		// 2. 根据配置创建会话对象, 用于和邮件服务器交互
		Session session = Session.getDefaultInstance(properties);
		session.setDebug(true);
		// 3. 创建一封邮件
		MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount);
		// 4.根据session获取邮件传输对象
		Transport transport = session.getTransport();
		// 5.使用邮箱账号和密码连接邮件
		// 邮箱必须保持一致
		transport.connect(myEmailAccount, myEmailPassword);
		// 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人,
		// 密送人
		transport.sendMessage(message, message.getAllRecipients());
		// 7.关闭连接
		transport.close();
	}

	public static MimeMessage createMimeMessage(Session session, String sendEmail, String receiveEmail)
			throws Exception {
		// 1.创建邮件对象
		MimeMessage message = new MimeMessage(session);
		// 2.From，发件人
		message.setFrom(new InternetAddress(sendEmail, "发送邮件", "UTF-8"));
		// 3.to，接收人
		message.addRecipient(RecipientType.TO, new InternetAddress(receiveEmail, "接收邮件", "UTF-8"));
		// 4.subject，邮件主题
		message.setSubject("测试邮件", "UTF-8");

		/*
		 * 邮件内容的创建
		 */

		// 5.创建图片节点
		MimeBodyPart image = new MimeBodyPart();
		DataHandler dHandler = new DataHandler(new FileDataSource("E:\\资料\\图片\\superman.jsp"));
		image.setDataHandler(dHandler);
		image.setContentID("image");
		// 6.创建文本节点
		MimeBodyPart text = new MimeBodyPart();
		text.setContent("这是图片<br/><img src='cid:image'/>", "text/html;charset=utf-8");
		// 7. （文本+图片）设置 文本 和 图片 “节点”的关系（将 文本 和 图片 “节点”合成一个混合“节点”）
		MimeMultipart mm_test_image = new MimeMultipart();
		mm_test_image.addBodyPart(text);
		mm_test_image.addBodyPart(image);
		mm_test_image.setSubType("related");// 关联关系

		// 8. 将 文本+图片 的混合“节点”封装成一个普通“节点”
		// 最终添加到邮件的 Content 是由多个 BodyPart 组成的 Multipart, 所以我们需要的是 BodyPart,
		// 上面的 mm_text_image 并非 BodyPart, 所有要把 mm_text_image 封装成一个 BodyPart
		MimeBodyPart text_img = new MimeBodyPart();
		text_img.setContent(mm_test_image);

		// 9.创建附件节点
		MimeBodyPart attachment = new MimeBodyPart();
		DataHandler dataHandler2 = new DataHandler(new FileDataSource("C:\\Users\\HASEE\\Desktop\\webService.doc")); // 读取本地文件
		attachment.setDataHandler(dataHandler2);
		attachment.setFileName(MimeUtility.encodeText(dataHandler2.getName()));

		// 10, 设置（文本+图片）和 附件 的关系（合成一个大的混合“节点” / Multipart ）
		MimeMultipart mimeMultipart = new MimeMultipart();
		mimeMultipart.addBodyPart(text_img);
		mimeMultipart.addBodyPart(attachment);
		mimeMultipart.setSubType("mixed");
		// 11. 设置整个邮件的关系（将最终的混合“节点”作为邮件的内容添加到邮件对象）
		message.setContent(mimeMultipart);
		// 12. 设置发件时间
		message.setSentDate(new Date());
		message.saveChanges();
		return message;

	}

}
