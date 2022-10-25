package com.example.complete_backend_springboot_lms.service;

import com.example.complete_backend_springboot_lms.exception.UserDefinedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    /********************************************************************************************************************************
     * Ability to send a otpNumber to given emailId for emailVerification
     * @param mail
     * @param subject
     * @param text
     *******************************************************************************************************************************/

    public void sendEmail(String mail, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("immanuveljeeva2000@gmail.com");
            message.setTo(mail);
            message.setSubject(subject);
            message.setText(text);
            message.setSentDate(new Date());
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new UserDefinedException("Mail was not sent");
        }
    }

    public void sendLink(String emailId, String subject, String text) {
        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            message.setFrom("immanuveljeeva2000@gmail.com");
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailId));

            message.setSubject(subject);
            // Create the message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setText(text, "UTF-8", "html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Put parts in message
            message.setContent(multipart);

            // Send the message
            javaMailSender.send(message);

        } catch (MessagingException e) {
            throw new UserDefinedException("Message Not sent");
        }
    }

    public void deleteEmail(String pop3Host, String storeType, String user, String password) {
        String emailNo = null;
        Properties props = new Properties();

        Session session = Session.getInstance(props);

        try {
            Store store = session.getStore(storeType);
            store.connect(pop3Host, user, password);

            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_WRITE);

            Message[] messages = folder.getMessages();
            System.out.println("Total Message - " + messages.length);

            Flags seen = new Flags(Flags.Flag.SEEN);
            FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
            Message[] messages1 = folder.search(unseenFlagTerm);
            System.out.println(messages1.length);

            for (int i = 0; i < 2 ; i++) {
                Message emailMessage = messages[i];
                System.out.println();
                System.out.println("Email = " + (i + 1));
                System.out.println("Subject - " + emailMessage.getSubject());
                System.out.println("From - " + emailMessage.getFrom()[0]);
                if("immanuveljeeva2000@gmail.com".equalsIgnoreCase(emailMessage.getFrom()[0].toString())){
                    emailNo = i+"";
                }
            }
            System.out.println(emailNo);

            messages[Integer.parseInt(emailNo) - 1].setFlag(Flags.Flag.DELETED, true);
            folder.close(true);
            store.close();

            System.out.println("Email" + " deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error in deleting email.");
        }
    }
}