package com.digital.controller;

import com.digital.model.*;
import com.digital.model.api.HttpSms;
import com.digital.model.response.SintreObseqPlus;
import com.digital.model.response.SintreRenteEduc;
import com.digital.model.response.SintreTempDeces;
import com.digital.repository.*;
import com.digital.service.SMSHelper;
import com.digital.util.Constant;
import com.digital.util.Utility;
import com.google.gson.Gson;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
     private final BrancheRepository brancheRepository;
     private final ProduitRepository produitRepository;
     private final TypesProduitRepository typesProduitRepository;
     private final UserRepository userRepository;
     private final SessionModel session;
     private final SMSHelper smsHelper;
     private final VueProduitRepository vueProduitRepository;
 /*   Branche branche;
    Branche branche2;
    Branche branche3;
    TypesProduit typesProduit;
    TypesProduit typesProdui2;*/

    @Autowired
    public HomeController(BrancheRepository brancheRepository, ProduitRepository produitRepository, TypesProduitRepository typesProduitRepository, UserRepository userRepository, SessionModel session, SMSHelper smsHelper,VueProduitRepository vueProduitRepository) {
        this.brancheRepository = brancheRepository;
        this.produitRepository = produitRepository;
        this.typesProduitRepository = typesProduitRepository;
        this.userRepository = userRepository;
        this.vueProduitRepository = vueProduitRepository;
        this.session = session;
        this.smsHelper = smsHelper;
       /* branche = brancheRepository.findOne(1L);
        branche2 = brancheRepository.findOne(2L);
        branche3 = brancheRepository.findOne(3L);
        typesProduit = typesProduitRepository.findOne(1L);
        typesProdui2 = typesProduitRepository.findOne(2L);*/

    }


    @ModelAttribute
    void injectProduitModel(Model model) {

    }

    @GetMapping("/")
    public String index(Model model) {
        System.out.println("HomeController.class");
        Long[] codes = {1L, 3L, 32L};
        long debut = DateTime.now().getMillis();
        System.out.println(DateTime.now().getMillis());
        //List<Produit> produitsParticulierEpargne = produitRepository.findProduitsByBrancheAndTypesProduit(brancheRepository.findOne(1L),typesProduitRepository.findOne(1L));
        List<VueProduit> produitsParticulierEpargne = vueProduitRepository.findByIdeBrancheAndIdeTypeAndCodeIsNotIn(1L, 1L, codes);
        List<VueProduit> produitsParticulierPrevoyance = vueProduitRepository.findByIdeBrancheAndIdeType(2L, 1L);
        List<VueProduit> produitsParticulierMixte = vueProduitRepository.findByIdeBrancheAndIdeType(3L, 1L);

        List<VueProduit> produitsProfessionnelsEpargne = vueProduitRepository.findByIdeBrancheAndIdeType(1L, 2L);
        List<VueProduit> produitsProfessionnelsPrevoyance = vueProduitRepository.findByIdeBrancheAndIdeType(2L, 2L);

        model.addAttribute("produitsParticulierEpargne", produitsParticulierEpargne);
        model.addAttribute("produitsParticulierPrevoyance", produitsParticulierPrevoyance);
        model.addAttribute("produitsParticulierMixte", produitsParticulierMixte);
        model.addAttribute("produitsProfessionnelsEpargne", produitsProfessionnelsEpargne);
        model.addAttribute("produitsProfessionnelsPrevoyance", produitsProfessionnelsPrevoyance);
        long fin = DateTime.now().getMillis();
        System.out.println("Class : HomeController, methode : index, url :/  temps :"+(fin-debut));
        return "index";
    }

    @GetMapping("/produit/{id}")
    public String produit(@PathVariable Long id, Model model) {
        Produit produit = produitRepository.findFirstByCode(id);
        model.addAttribute("product", produit);
        // System.out.println("produit  :"+produit.getNomProduit());
        return "produit";
    }

    @GetMapping("/creer_compte")
    public String creer_compte(Model model, RedirectAttributes redirectAttributes, HttpServletRequest request,
                               @RequestParam(value = "user", required = false) User user,
                               @RequestParam(value = "success", required = false) boolean success) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }
        return "creer_compte";
    }

    @GetMapping("/desherence")
    public String contratDesherence(Model model, RedirectAttributes redirectAttributes, HttpServletRequest request,
                               @RequestParam(value = "user", required = false) User user,
                               @RequestParam(value = "success", required = false) boolean success) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }
        return "contrat-desherence";
    }

    @GetMapping("/forgetPassword")
    public String forgetPassword(Model model){
        return "forget_password";
    }

    @RequestMapping(value = "/check-id", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public @ResponseBody Map<String, Object>checkId(String identifiant,HttpServletRequest request) {
        System.out.println("session:"+ request.getSession().getAttribute("code"));
        Map<String, Object> response = new HashMap<>();

        System.out.println("identifiant " + identifiant);
        User user = userRepository.findFirstByUsername(identifiant);
        if (user != null){
            request.getSession().setAttribute("username",identifiant);
            response.put("user",user.getLastname()+" "+user.getFirstname());
            System.out.println("user :"+user.getFirstname() +" "+user.getLastname());
            response.put("success",true);
            response.put("title","Info");
            response.put("message","Entrer votre numero de téléphone pour recevoir votre code");
        }else {
            response.put("success",false);
            response.put("title","Info");
            response.put("message","Desolé aucun compte ne correspond a cet identifiant");
        }
        return response;
    }

    @RequestMapping(value = "/number-code", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public @ResponseBody Map<String, Object>getNumberAndsendCode(String numero,HttpServletRequest request) {
        System.out.println("numero :"+numero);
        String code  = Utility.getCodeReinit(6,"1234567890");
        HttpSms httpSms = new HttpSms.Builder("Saham vie",String.format("225%s",numero),String.format("%s est le code de réinitialisation de votre mot de passe",code),"UTF-8").build();
        smsHelper.sendTo(httpSms);
        Map<String, Object> response = new HashMap<>();
        request.getSession().setAttribute("code",code);
        response.put("success",true);
        response.put("title","Info");
        response.put("message","Vous avez du recevoir un code par  texto,ce code contient 6 caracteres.");
        return response;
    }

    @RequestMapping(value = "/check-sent-code", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public @ResponseBody Map<String, Object>checkSentCode(String code,HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        System.out.println("code :"+code);
        if (code.equals(request.getSession().getAttribute("code"))){
            System.out.println("code identique");
            response.put("success",true);
            response.put("title","Info");
            response.put("message","Veuillez a present changer votre mot de passe");

        }else {
            System.out.println("code different");
            response.put("success",false);
            response.put("title","Info");
            response.put("message","Le code que vous avez entré est incorrect");
        }
        return response;
    }


    @RequestMapping(value = "/reset-password", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public @ResponseBody Map<String, Object>resetPassword(String password,HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        System.out.println("password :"+password);
        response.put("success",false);
        response.put("title","Info");
        response.put("message","une erreur s'est produite lors de la réinitialisation,veuiller reesayer plus tard");

        if (request.getSession().getAttribute("username") !=null){
            User user = userRepository.findFirstByUsername(request.getSession().getAttribute("username").toString());
            if (user != null){
                user.setPassword(password);
                userRepository.save(user);
                response.put("success",true);
                response.put("title","Info");
                response.put("message","Votre mot de passe à bien été réinitialisé");
            }
        }

        return response;
    }


    @GetMapping("/forgetIdentifiant")
    public String forgetId(Model model){
        return "forget_id";
    }

    @GetMapping("/login")
    public String login(Model model, RedirectAttributes redirectAttributes, HttpServletRequest request,
                        @RequestParam(value = "username", required = false) String username,
                        @RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout) {
        if (!model.containsAttribute("user")) {
            System.out.println("pas user");
            model.addAttribute("user", new User());
        }
        if (error != null) {
            System.out.println("erreur");
            redirectAttributes.addFlashAttribute("username", username);
            return "redirect:/login";
        }
        if (logout != null) {
            System.out.println("deconnexion");
            System.out.println("request => "+request.getSession().toString());
            if (session !=null){
                System.out.println("username :"+session.getUsername());
                System.out.println("sessionToString :"+session.getIdentifiant());
            }
            redirectAttributes.addFlashAttribute("logout", "Vous etes deconnecté");
            return "redirect:/";
        }
        System.out.println("request ");
        request.getSession().setAttribute("username", username);
        return "login";
    }

    @GetMapping("/disconnect")
    public String logout(HttpServletRequest request , HttpServletResponse response) {
        User user = userRepository.findFirstByUsername(session.getUsername());
        System.out.println("id de celui qui se deconnect"+session.getUsername());
        if (user !=null){
            user.setState(Constant.DISCONNECTED);
            userRepository.save(user);
        }

        request.getSession().removeAttribute("username");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.

    }

    @GetMapping("/branche")
    public String branche() {
        System.out.println("branche");
        return "branche";
    }

    @GetMapping("/valide")
    public String valide() {
        return "valide";
    }

    @GetMapping("/contrat")
    public String contrat() {
        return "contrat";
    }

    @GetMapping("/activation")
    public String activation() {
        return "activation";
    }

    @GetMapping("/darshboard")
    public String darshboard() {
        return "darshboard";
    }


}
