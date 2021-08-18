package edu.epam.webproject.util;

import edu.epam.webproject.controller.command.CommandType;
import edu.epam.webproject.controller.command.RequestParameter;
import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Queue;


public class MailSender {
    private static final String QUESTION_SIGN = "?";
    private static final String EQUALS_SIGN = "=";
    private static final String AMPERSAND_SIGN = "&";


    private static final Logger logger = LogManager.getLogger();


    private static final String RESOURCE_FILE = "\\email.properties";
    private static final Properties properties = new Properties();
    private static final String MAIL_USER_NAME = "mail.user.name";
    private static final String MAIL_USER_PASSWORD = "mail.user.password";
    private static final String MAIL_FROM = "mail.from";
    private static final String MAIL_REGISTER_SUBJECT = "Registration in WorldFlat";
    private static final String MAIL_CHANGE_PASSWORD_SUBJECT = "Change account password in WorldFlat";
    private static final String DEPLOYMENT = "http://localhost:8080/WebProject_war_exploded/";

    static {
        try(InputStream stream = MailSender.class.getClassLoader().getResourceAsStream(RESOURCE_FILE)){
            properties.load(stream);
        } catch (IOException exception) {
            logger.log(Level.ERROR, "Unable to find properties file: " + RESOURCE_FILE);
        }
    }

    public static void sendRegisterLetter(String email){
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
            message.setSubject(MAIL_REGISTER_SUBJECT);
            message.setText(buildRegisterConfirmLink(email));
            Transport.send(message);
        }catch (MessagingException e) {
            logger.log(Level.ERROR, "Problems with sending email to " + email);
        }
    }
    public static void setForgetPasswordLetter(String email){
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
            message.setSubject(MAIL_CHANGE_PASSWORD_SUBJECT);
            message.setText(buildChangeUserPasswordLink(email));
            Transport.send(message);
        } catch (MessagingException e) {
            logger.log(Level.ERROR, "Problems with sending email to " + email);
        }
    }
    private static String buildRegisterConfirmLink(String email){
        StringBuilder builder = new StringBuilder();
        builder.append(DEPLOYMENT);
        builder.append(RequestParameter.CONTROLLER);
        builder.append(QUESTION_SIGN);
        builder.append(RequestParameter.COMMAND);
        builder.append(EQUALS_SIGN);
        builder.append(CommandType.ACTIVATE_ACCOUNT_COMMAND.toString().toLowerCase());
        builder.append(AMPERSAND_SIGN);
        builder.append(RequestParameter.EMAIL);
        builder.append(EQUALS_SIGN);
        builder.append(email);
        return builder.toString();
    }
    private static String buildChangeUserPasswordLink(String email){
        StringBuilder builder = new StringBuilder();
        builder.append(DEPLOYMENT);
        builder.append(RequestParameter.CONTROLLER);
        builder.append(QUESTION_SIGN);
        builder.append(RequestParameter.COMMAND);
        builder.append(EQUALS_SIGN);
        builder.append(CommandType.GO_TO_CHANGE_USER_PASSWORD_PAGE_COMMAND.toString().toLowerCase());
        builder.append(AMPERSAND_SIGN);
        builder.append(RequestParameter.EMAIL);
        builder.append(EQUALS_SIGN);
        builder.append(email);
        return builder.toString();
    }
}
