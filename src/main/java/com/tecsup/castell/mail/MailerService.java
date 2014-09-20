package com.tecsup.castell.mail;

import com.tecsup.castell.helper.AppConstant;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailerService {

    @Autowired
    JavaMailSender mailSender;

    @Async
    public void sendMail(String to, String subject, String body) {
        try {
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();

            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setSubject(subject);
            message.setFrom(new InternetAddress(AppConstant.EMAIL_SOPORTE));
            message.setTo(new InternetAddress(to));

            message.setText(body, true);

            this.mailSender.send(mimeMessage);

        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

}
