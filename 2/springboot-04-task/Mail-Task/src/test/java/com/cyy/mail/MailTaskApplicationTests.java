package com.cyy.mail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class MailTaskApplicationTests {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    void simpleMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件通知
        message.setSubject("通知-今晚开会");
        message.setText("<h1>今晚7:30开会</h1>");

        message.setTo("cyy576779239@163.com");
        message.setFrom("576779239@qq.com");

        mailSender.send(message);

    }

    @Test
    void mimeMessage() throws MessagingException {

        //创建一个复杂的消息邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        //邮件通知
        helper.setSubject("通知-今晚开会");
        helper.setText("<b style='color:red'>今晚8:00开会</b>",true);

        helper.setTo("cyy576779239@163.com");
        helper.setFrom("576779239@qq.com");

        helper.addAttachment("1.jpg",new File("C:\\Users\\Dell\\Pictures\\壁纸照片\\96w8e8.jpg"));
        helper.addAttachment("2.jpg",new File("C:\\Users\\Dell\\Pictures\\壁纸照片\\1.jpg"));

        mailSender.send(mimeMessage);

    }
}
