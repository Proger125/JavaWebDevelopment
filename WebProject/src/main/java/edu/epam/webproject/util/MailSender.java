package edu.epam.webproject.util;

import edu.epam.webproject.controller.command.CommandType;
import edu.epam.webproject.controller.command.RequestParameter;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class MailSender {
    private static final Logger logger = LogManager.getLogger();
    private static final String RESOURCE_FILE = "\\email.properties";
    private static final Properties properties = new Properties();
    private static final String MAIL_USER_NAME = "mail.user.name";
    private static final String MAIL_USER_PASSWORD = "mail.user.password";
    private static final String MAIL_FROM = "mail.from";
    private static final String MAIL_SUBJECT = "Registration in WorldFlat";
    private static final String DEPLOYMENT = "http://localhost:8080/WebProject_war_exploded/";

    static {
        try(InputStream stream = MailSender.class.getClassLoader().getResourceAsStream(RESOURCE_FILE)){
            properties.load(stream);
        } catch (IOException exception) {
            logger.log(Level.ERROR, "Unable to find properties file: " + RESOURCE_FILE);
        }
    }

    public static void send(String email){
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty(MAIL_USER_NAME), properties.getProperty(MAIL_USER_PASSWORD));
            }
        });
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(properties.getProperty(MAIL_FROM)));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(MAIL_SUBJECT);
            message.setText(buildRegisterConfirmLink(email));
            Transport.send(message);
        }catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    private static String buildRegisterConfirmLink(String email){
        StringBuilder builder = new StringBuilder();
        builder.append(DEPLOYMENT);
        builder.append(RequestParameter.CONTROLLER);
        builder.append("?");
        builder.append(RequestParameter.COMMAND);
        builder.append("=");
        builder.append(CommandType.ACTIVATE_ACCOUNT_COMMAND.toString().toLowerCase());
        builder.append("&");
        builder.append(RequestParameter.EMAIL);
        builder.append("=");
        builder.append(email);
        return builder.toString();
    }
}
