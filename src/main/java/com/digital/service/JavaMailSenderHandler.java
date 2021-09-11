package com.digital.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * Handle mail sending
 *
 * @author babacoul
 */
@Service
public class JavaMailSenderHandler {

  private final JavaMailSender javaMailSender;

  private final Logger LOGGER = LoggerFactory.getLogger(JavaMailSenderHandler.class);

  @Autowired
  public JavaMailSenderHandler(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  @Async
  public void send(final String to, final String subject, final String message,
      final boolean html) throws MessagingException {

    LOGGER.info(String.format("Pending mail to %s... : Subject: %s", to, subject));

    MimeMessage mimeMessage = javaMailSender.createMimeMessage();

    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

    helper.setTo(to);
    helper.setText(message, html);
    helper.setSubject(subject);

    javaMailSender.send(mimeMessage);

    LOGGER.info("Mail sent successfully !!!");
  }

  @Async
  public void sendWithAtta(final String to, final String subject, final String message,
                           final boolean html, final FileSystemResource file) throws MessagingException {

    LOGGER.info(String.format("Pending mail to %s... : Subject: %s", to, subject));

    MimeMessage mimeMessage = javaMailSender.createMimeMessage();

    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

    helper.setTo(to);
    helper.setText(message, html);
    helper.setSubject(subject);
    helper.addAttachment(file.getFilename(),file);
    javaMailSender.send(mimeMessage);

    LOGGER.info("Mail sent successfully !!!");
  }

  @Async
  public void sendWithAtta(final String to, final String subject, final String message,
                           final boolean html, final List<FileSystemResource> files) throws MessagingException {

    LOGGER.info(String.format("Pending mail to %s... : Subject: %s", to, subject));

    MimeMessage mimeMessage = javaMailSender.createMimeMessage();

    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

    helper.setTo(to);
    helper.setText(message, html);
    helper.setSubject(subject);
    for (FileSystemResource fsr: files
         ) {
      helper.addAttachment(fsr.getFilename(),fsr);
    }
    javaMailSender.send(mimeMessage);

    LOGGER.info("Mail sent successfully !!!");
  }
}
