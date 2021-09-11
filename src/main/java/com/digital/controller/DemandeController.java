package com.digital.controller;

import com.digital.model.Demande;
import com.digital.model.SessionModel;
import com.digital.repository.ContratRepository;
import com.digital.repository.DemandeRepository;
import com.digital.repository.PersonneRepository;
import com.digital.repository.VilleRepository;
import com.digital.service.JavaMailSenderHandler;
import com.digital.service.MailTemplateHandler;
import com.digital.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


@Controller
public class DemandeController {

    @Autowired JavaMailSenderHandler javaMailSenderHandler;
    @Autowired MailTemplateHandler mailTemplateHandler;

    @Autowired SessionModel session;

    @Autowired PersonneRepository personneRepository;
    @Autowired ContratRepository contratRepository;
    @Autowired VilleRepository villeRepository;
    @Autowired DemandeRepository demandeRepository;



    @RequestMapping(value = "/demande/rachatTotalTest",method = RequestMethod.POST)
    public String askTotal(Model model, Long id){
        return "";

    }




}
