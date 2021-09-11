package com.digital.service;

import com.digital.model.api.HttpSms;
import com.digital.util.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SMSHelper {

    private static final String HTTP_SMS_API_BASE_URL = "http://gateway2.arolitec.com/interface/%s?%s";


    private static final Logger log = LoggerFactory.getLogger(SMSHelper.class);


    public ResponseEntity<String> sendTo(HttpSms message) {
        RestTemplate restTemplate = new RestTemplate();
        log.info(String.format("Envoie de sms  a %s... : contenu: %s", message.getReceiver(), message.getContent()));
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(String.format(HTTP_SMS_API_BASE_URL, "senderv2.php", message.toGetParamString()), String.class);
        log.info(String.format("Reponse %s... :", responseEntity));
        log.info("Sms sent successfully !!!");
        return responseEntity;
    }
}
