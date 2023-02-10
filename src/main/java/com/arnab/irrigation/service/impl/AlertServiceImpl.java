package com.arnab.irrigation.service.impl;

import com.arnab.irrigation.dto.EmailDTO;
import com.arnab.irrigation.service.AlertService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author Arnab Bhattacharyya
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AlertServiceImpl implements AlertService {
    
    @Value("${admin.email}")
    private String adminEmail;

    private JavaMailSender javaMailSender;

    @Override
    public void sendMailAlert(String deviceName) {

        log.info("sending iot device not available alert");
        var mail = new EmailDTO();
        mail.setTo(adminEmail);
        mail.setSubject("Iot Device Not Available Alert");
        String message = "IotDevice with name %s is not available";
        mail.setBody(String.format(message, deviceName));

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mail.getTo());
        msg.setSubject(mail.getSubject());
        msg.setText(mail.getBody());

        javaMailSender.send(msg);
    }
    
}
