package mx.edu.utez.adoptaMe.service;

public interface EmailService {

	boolean sendEmail(String emailTo, String emailSubject, String emailContent);

}
