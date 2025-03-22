package com.mlastovsky.service;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Map;

import static java.nio.charset.StandardCharsets.*;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;
    private static final String SENDER_EMAIL = "LastovskyTech@gmail.com";

    @Async
    public void sendEmail(String destinationEmail,
                          String subject,
                          String templateName,
                          Map<String, Object> variables) {
        try {
            var mimeMessage = mailSender.createMimeMessage();
            var mimeMessageHelper = new MimeMessageHelper(mimeMessage, MULTIPART_MODE_MIXED_RELATED, UTF_8.name());
            mimeMessageHelper.setFrom(SENDER_EMAIL);
            mimeMessageHelper.setTo(destinationEmail);
            mimeMessageHelper.setSubject(subject);

            var context = new Context();
            context.setVariables(variables);
            var htmlTemplate = templateEngine.process(templateName, context);
            mimeMessageHelper.setText(htmlTemplate, true);

            mailSender.send(mimeMessage);
            log.info("INFO - Email sent to {}, with template {}", destinationEmail, templateName);
        } catch (MessagingException ex) {
            log.warn("WARNING - Cannot send email to {}", destinationEmail);
        }
    }

}
