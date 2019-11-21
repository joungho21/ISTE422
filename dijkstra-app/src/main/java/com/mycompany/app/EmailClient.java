package com.mycompany.app;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailClient {

   public String recipient;
   public String subject;
   public String text;
   
   private static final String sender = "web@gmail.com";
   private static final String host = "localhost";

   public EmailClient(String text, String recipient, String subject) {
      this.recipient = recipient;
      this.subject = subject;
      this.text = text;
   }

   public void sendEmail() {    
      Properties properties = System.getProperties();

      properties.setProperty("mail.smtp.host", host);

      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);

      try {
         System.out.println("Made it into the try block");
         MimeMessage message = new MimeMessage(session);
         System.out.println("Good job, mimemessage created");
         message.setFrom(new InternetAddress(sender));
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
         message.setSubject(subject);
         message.setText(text);

         Transport.send(message);
      } catch (Exception err) {
         System.out.println("ERROR: Unable to send email");
         err.printStackTrace();
      }
   }
}