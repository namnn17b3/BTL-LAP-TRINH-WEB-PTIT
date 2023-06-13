package fruitshop.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.DataHandler;
import javax.activation.DataSource;

public class Email {
	static final String from = "anhnam2730@gmail.com";
	static final String password = "zgcmjkclyspyvtvo";
	
	public static void sendMail(String to, String noiDung) {
		// Properties: khai báo các thuộc tính
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com"); // smtp host
		properties.put("mail.smtp.port", "587"); // TLS 587, SSL 465
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		
		// tạo authenticator
		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		};
		
		// tạo phiên làm việc
		Session session = Session.getInstance(properties, authenticator);
		
		// tạo một tin nhắn
		MimeMessage msg = new MimeMessage(session);
		try {
			// kiểu nội dung
			msg.addHeader("Content-type", "text/html; charset=UTF-8");
			
			// Người gửi
			msg.setFrom(from);

			// Người nhận
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			
			// tiêu đề mail
			msg.setSubject("Xác thực tài khoản");
			
			// Quy định ngày gửi
			msg.setSentDate(new Date());
			
			// Quy định email nhận phản hồi
			// msg.setReplyTo(InternetAddress.parse(from, false));
			
			msg.setContent(noiDung, "text/html; charset=UTF-8");
		
			Transport.send(msg);
			System.out.println("Sent email success!");
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error!");
			e.printStackTrace();
		}
	}

	protected static PasswordAuthentication PasswordAuthentication(String from2, String password2) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	public static void main(String[] args) {
//		sendMail("wayluanmtp@gmail.com", RanDomCode.randomCode());
//	}
}
