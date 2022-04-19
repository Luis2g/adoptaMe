package mx.edu.utez.adoptaMe.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

// import com.sendgrid.Method;
// import com.sendgrid.Request;
// import com.sendgrid.Response;
// import com.sendgrid.SendGrid;
// import com.sendgrid.helpers.mail.Mail;
// import com.sendgrid.helpers.mail.objects.Content;
// import com.sendgrid.helpers.mail.objects.Email;

@Service
public class EmailServiceImpl implements EmailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	private JavaMailSender sender;

	@Override
	public boolean sendEmail(String emailTo, String emailSubject, String emailContent) {
		LOGGER.info("EmailBody: {}", emailContent);
		return sendEmailTool(emailContent,emailTo, emailSubject);
	
	}

	// @Value("${sendgrid.api.key}")
	// private String sendgridApiKey;
	
	// @Value("${sendgrid.api.email}")
	// private String emailFrom;

	// @Override
	// public boolean sendEmail(String emailTo, String emailSubject, String emailContent) {
	// 	Email from = new Email(emailFrom);
	// 	Email to = new Email(emailTo);
	// 	Content content = new Content("text/html", emailContent); // text/plain or text/html
	// 	Mail mail = new Mail(from, emailSubject, to, content);

	// 	SendGrid sg = new SendGrid(sendgridApiKey);
	// 	Request request = new Request();
	// 	try {
	// 		request.setMethod(Method.POST);
	// 		request.setEndpoint("mail/send");
	// 		request.setBody(mail.build());
	// 		Response response = sg.api(request);

	// 		System.out.println(response.getStatusCode());
	// 		System.out.println(response.getBody());
	// 		System.out.println(response.getHeaders());

	// 		return response.getStatusCode() == 202;
	// 	} catch (Exception exception) {
	// 		System.err.println(exception.getMessage());
	// 		return false;
	// 	}
	// }

	private boolean sendEmailTool(String textMessage, String email,String subject) {
		boolean send = false;
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);		
		try {
			helper.setTo(email);
			helper.setText(textMessage, true);
			helper.setSubject(subject);
			sender.send(message);
			send = true;
			LOGGER.info("Mail enviado!");
		} catch (MessagingException e) {
			LOGGER.error("Hubo un error al enviar el mail: {}", e);
		}
		return send;
	}	

}
