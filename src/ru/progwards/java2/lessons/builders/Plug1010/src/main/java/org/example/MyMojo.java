package org.example;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;


/**
 * <configuration>
 * <emailTo>emailTo@domen.ru</emailTo>
 * <emailFrom>emailFrom@domen.ru</emailFrom>
 * <authServ>smtp.domen.ru</authServ>
 * <authUser>...</authUser>
 * <authPass>...</authPass>
 * <port> </port>
 * </configuration>
 */
@Mojo(name = "touch", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class MyMojo
        extends AbstractMojo {
    Properties prop = new Properties();
    DateTimeFormatter dateF = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    @Parameter(property = "authUser")
    String authUser;
    @Parameter(property = "authPass")
    String authPass;
    @Parameter(property = "emailTo")
    String emailTo;
    @Parameter(property = "emailFrom")
    String emailFrom;
    @Parameter(property = "authServ")
    String authServ;
    @Parameter(property = "port")
    String port;

    @Parameter(property = "path")
    String path;


    public void execute()
            throws MojoExecutionException {


        prop.put("mail.smtp.host", authServ);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.ssl.trust", authServ);
        prop.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        prop.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        prop.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication


        System.out.println("prop=" + prop);

        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(authUser, authPass);
            }
        });

        try {
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();

            new File(path).renameTo(new File("Calc.rename"));
            attachmentBodyPart.attachFile("Calc.rename");


            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(emailFrom));

            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(emailTo));
            message.setSubject("Mail from Maven plugin");
            System.out.println("      after session message.setSubject    ");
            String msg =
                    "Calc comands:\n<br>" +
                            "<b>\"sum a b\"</b>  , return int a+b\n<br>" +
                            "<b>\"diff a b\"</b> , return int a-b\n<br>" +
                            "<b>\"mult a b\"</b> , return int a*b\n<br>" +
                            "<b>\"div a b\"</b>  , return int a/b\n<br>" +
                            "Compile at: " + LocalDateTime.now().format(dateF) + "<br>" +
                            "* rename \"Calc.rename\" to \"Calc.jar\"";

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html");
            System.out.println("      after imeBodyPart.setContent(msg, \"text/html\");  \n" + "----msg: " + msg);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            System.out.println("      after multipart.addBodyPart(mimeBodyPart);   ");
            message.setContent(multipart);

            System.out.println("      message all:" + message.getAllRecipients());
            multipart.addBodyPart(attachmentBodyPart);

            Transport.send(message);
            System.out.println("      after send   ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
