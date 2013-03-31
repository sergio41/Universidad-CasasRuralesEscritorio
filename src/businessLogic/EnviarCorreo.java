package businessLogic;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviarCorreo {

	//public static void main(String[] args) {
	public static void enviarCorreos( String dest, String asu, String mensa){
		String servidorSMTP = "smtp.gmail.com";
		String puerto = "587";
		String usuario = "villatripasdearribacr@gmail.com";
		String password = "villavilla";

		String destino = dest;//"urko.larm@gmail.com";
		String asunto = asu;//"Prueba!";
		String mensaje = mensa;//"Este es un mensaje de prueba.";

		Properties props = new Properties();

		props.put("mail.debug", "false");
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", servidorSMTP);
		props.put("mail.smtp.port", puerto);

		Session session = Session.getInstance(props, null);

		try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
			message.setSubject(asunto);
			message.setSentDate(new Date());
			message.setText(mensaje);
			Transport tr = session.getTransport("smtp");
			tr.connect(servidorSMTP, usuario, password);
			message.saveChanges();
			tr.sendMessage(message, message.getAllRecipients());
			tr.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}