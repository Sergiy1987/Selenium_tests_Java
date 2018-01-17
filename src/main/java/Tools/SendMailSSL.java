package Tools;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;
public class SendMailSSL {
    public static void send(String login, String password, String to, String subject, String text) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");//587
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");//465
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(login,password);
                    }
                });
        try {
            //SimpleDateFormat dateFormat = new SimpleDateFormat("DD-MM-YYYY hh:mm:ss zzz");
            //Date date = new Date();
            //System.out.println(dateFormat.format(date));
            Message message = new MimeMessage(session);
            Multipart multipart = new MimeMultipart();
            MimeBodyPart htmlPart = new MimeBodyPart();
            MimeBodyPart txtPart = new MimeBodyPart();

            message.setFrom(new InternetAddress(login));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);

            String htmlContent = "<html><body><font color=red>Greetings!</font><br>";
            htmlContent += "<b>Test Passed successfully!</b><br>";
            htmlContent += "From: "+ login + "<br>"
                            + "To: " + to + "<br>"
                            + "Subject: " + subject + "<br>"
                            + "Data: " + new Date() + "<br>"
                            + "<b>Result from DataBase: " + "</b>"
                            + "</body></html>";

            htmlPart.setContent(htmlContent, "text/html;charset=UTF-8");
            multipart.addBodyPart(htmlPart);
            message.setContent(multipart);

            txtPart.setText(text);
            multipart.addBodyPart(txtPart);

           // message.setText(text);
            Transport.send(message);
            System.out.println("Sent message successfully...");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
