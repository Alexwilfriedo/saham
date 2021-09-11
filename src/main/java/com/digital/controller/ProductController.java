package com.digital.controller;

import com.digital.model.Produit;
import com.digital.repository.ProduitRepository;
import com.digital.service.JavaMailSenderHandler;
import com.digital.service.MailTemplateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.context.Context;

import org.thymeleaf.TemplateEngine;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Controller
public class ProductController {

    @Autowired private ProduitRepository produitRepository;
    @Autowired JavaMailSenderHandler javaMailSenderHandler;
    @Autowired MailTemplateHandler mailTemplateHandler;

    @RequestMapping(value = "/detail/product",method = RequestMethod.POST)
    public String detailProduit(Model model, Long id,String produit, String nom, String tel, String email) throws ExecutionException, InterruptedException {
        HashMap<String, Object> data = new HashMap<>();
        System.out.println("id "+id);
        Produit product = produitRepository.findOne(id);
        model.addAttribute("product",product);
        model.addAttribute("nom",nom);
        System.out.println("produit "+produit);
        System.out.println("nom "+nom);
        System.out.println("tel "+tel);
        System.out.println("email "+email);

       try {
           Context context = new Context();
           context.setVariable("nom",nom);
           context.setVariable("tel",tel);
           context.setVariable("email",email);
           context.setVariable("produit",produit);
           Future<String> content = mailTemplateHandler.build("mailTemplates/demandeInformation",context);
            javaMailSenderHandler.send("info.vie.ci@sahamassurance.com","demande de detail produit "+produit,content.get().toString(),true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        model.addAttribute("success",true);
        model.addAttribute("title","Info");
        model.addAttribute("message","merci pour votre interessement ,vous serez contacté");
        return "produit";
    }
}
