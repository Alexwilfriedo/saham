package com.digital.controller;

import com.digital.model.*;
import com.digital.model.api.HttpSms;
import com.digital.repository.*;
import com.digital.service.JavaMailSenderHandler;
import com.digital.service.MailTemplateHandler;
import com.digital.service.SMSHelper;
import com.digital.util.Constant;
import com.digital.util.SmsProvider;
import com.digital.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.LongStream;

import static com.digital.util.Utility.getRef;

@Controller
public class CompteController {

    @Autowired SessionModel sessionModel;
    @Autowired ContratRepository contratRepository;
    @Autowired PersonneRepository personneRepository;
    @Autowired UserRepository userRepository;
    @Autowired RoleRepository roleRepository;
    @Autowired ContratDesherenceRepository contratDesherenceRepository;
    @Autowired MailTemplateHandler mailTemplateHandler;
    @Autowired JavaMailSenderHandler javaMailSenderHandler;
    @Autowired private SMSHelper smsHelper;

    @RequestMapping(value = "/activate/compte", method = RequestMethod.POST)
    public String activateAccount(Model model, HttpServletRequest request, String username, RedirectAttributes redirectAttributes) {
        if (username != null) {
            User user = userRepository.findFirstByUsername(username);
            if (user != null) {
                if (!user.isEnabled()) {
                    user.setEnabled(true);
                    user.setActivationDate(new Date());
                    user.setPasswordConfirmation(user.getPassword());
                    userRepository.save(user);
                    redirectAttributes.addFlashAttribute("success", true);
                    redirectAttributes.addFlashAttribute("title", "Activation :");
                    redirectAttributes.addFlashAttribute("message", "Bienvenue  " + user.getCivilite() + " " + user.getFirstname() + " " + user.getLastname());
                    sessionModel.setUsername(username);
                    sessionModel.setId(user.getId());

                    request.getSession().setAttribute("username", username);

                    /*sessionModel.setIdentifiant();*/
                    return "redirect:/portailClient/darshboard";
                } else {
                    redirectAttributes.addFlashAttribute("success", false);
                    redirectAttributes.addFlashAttribute("title", "Erreur");
                    redirectAttributes.addFlashAttribute("message", "Votre compte est deja actif, veuillez vous connecter");
                    return "redirect:/login";
                }
            } else {
                redirectAttributes.addFlashAttribute("success", false);
                redirectAttributes.addFlashAttribute("title", "Erreur");
                redirectAttributes.addFlashAttribute("message", "identifiant inconnu");
                return "redirect:/activation";
            }

        } else {
            redirectAttributes.addFlashAttribute("success", false);
            redirectAttributes.addFlashAttribute("title", "Erreur");
            redirectAttributes.addFlashAttribute("message", "identifiant est obligatoire");
            return "redirect:/activation";
        }
    }

    @RequestMapping(value = "/creer/compte", method = RequestMethod.POST)
    public String inscription(Model model, @ModelAttribute User user, String police, String date_naissance, String phone, BindingResult result, RedirectAttributes redirectAttributes, HttpServletRequest request) throws ParseException {
        Personne personneOfContrat;
        System.out.println("A" + user.getPassword());
        System.out.println("B" + user.getPasswordConfirmation());
        if (result.hasErrors() || !new BCryptPasswordEncoder().matches(user.getPasswordConfirmation(), user.getPassword())) {
            redirectAttributes.addFlashAttribute("success", false);
            redirectAttributes.addFlashAttribute("title", "Erreur");
            if (!new BCryptPasswordEncoder().matches(user.getPasswordConfirmation(), user.getPassword())) {
                System.out.println("password different");
                redirectAttributes.addFlashAttribute("message", "les mots de passes sont differents");
                result.addError(new FieldError("user", "password", "les mots de passe sont differents"));
            }
            else redirectAttributes.addFlashAttribute("message", "Verifier que vous avez correctement rempli les champs");

            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("police", police);
            redirectAttributes.addFlashAttribute("date_naissance", date_naissance);

            //return "creer_compte";
            return "redirect:/creer_compte";
            // return "creer_compte";
        }
        else {
            Contrat contrat = contratRepository.findFirstByPolice(police);
            if (contrat != null) {
                Long id_souscr = contrat.getId_souscrpteur();
                 personneOfContrat = personneRepository.findByReference(id_souscr);
                if (personneOfContrat != null) {
                    long ref =(long)personneOfContrat.getReference();
                    System.out.println("matricule "+personneOfContrat.getMatricule());
                    System.out.println("ref :"+personneOfContrat.getReference());
                    if (LongStream.of(Constant.refCorporate).anyMatch(x -> x == ref)){
                        System.out.println("c est element d'un corporate");
                        System.out.println("Assuré :nom "+ contrat.getAssure().getLastname() +"prenom "+contrat.getAssure().getFirstname()+"reference :"+contrat.getAssure().getReference());
                        personneOfContrat = contrat.getAssure();
                    }
                    Date date = personneOfContrat.getBirthdate();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    SimpleDateFormat formater = new SimpleDateFormat("MM-dd-yyyy");
                    // formatter.parse(date_naissance);


                    if (date != null && formatter.format(date) != null) {
                        System.out.println("souscripteur ou assuré par date de naissance: ");
                        System.out.println("date 1: " + formatter.format(date));
                        System.out.println("date user non format: " + date_naissance);
                        //  System.out.println("date user: "+formater.format(user.getBirthdate()));
                        if (formatter.format(date).equalsIgnoreCase(date_naissance.replaceAll("/", "-"))) {
                            User user1 = new User();

                            System.out.println("egalite entre les 2 personnes: ");
                            user1.setLastname(user.getLastname());
                            System.out.println("lastname: " + user1.getLastname());
                            user1.setFirstname(user.getFirstname());
                            System.out.println("firstname: " + user1.getFirstname());
                            user1.setPassword(user.getPasswordConfirmation());
                            user1.setPasswordConfirmation(user.getPasswordConfirmation());
                            System.out.println("password: " + user1.getPassword());
                            user1.setPhone(user.getPhone());
                            System.out.println("PHONE: " + user1.getPhone());
                            user1.setBirthdate(date);
                            System.out.println("date de naissance: " + user1.getBirthdate());
                            user1.setSexe(personneOfContrat.getSexe());
                            System.out.println("SEXE: " + user1.getSexe());
                            user1.setCivilite(personneOfContrat.getCivilite());
                            System.out.println("CIVILTE: " + user1.getCivilite());
                            user1.setFonction(personneOfContrat.getFonction());

                            System.out.println("Fonction: " + user1.getFonction());
                            if (personneOfContrat.getVille() != null && !personneOfContrat.getVille().isEmpty()) {
                                System.out.println("ville: " + personneOfContrat.getVille());
                                user1.setVille(personneOfContrat.getVille());
                            }
                            if (personneOfContrat.getMatricule() != null) {
                                System.out.println("Matricule: " + personneOfContrat.getMatricule());
                                user1.setMatricule(personneOfContrat.getMatricule());
                            }
                            if (personneOfContrat.getBoitePostale() != null) {
                                System.out.println("Boite postale: " + personneOfContrat.getBoitePostale());
                                user1.setBoitePostale(personneOfContrat.getBoitePostale());
                            }
                            if (personneOfContrat.getPostalCode() != null) {
                                System.out.println("POSTALE CODE: " + personneOfContrat.getPostalCode());
                                user1.setBoitePostale(personneOfContrat.getPostalCode());
                            }
                            if (user.getEmail() != null ) {
                                if (userRepository.findByEmail(user.getEmail()) != null ){
                                    System.out.println("Erreur mail: ");
                                    redirectAttributes.addFlashAttribute("success", false);
                                    redirectAttributes.addFlashAttribute("message", "Cette adresse email est déjà utililsée");
                                    redirectAttributes.addFlashAttribute("title", "Erreur");
                                    redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
                                    redirectAttributes.addFlashAttribute("user", user);
                                    redirectAttributes.addFlashAttribute("police", police);
                                    redirectAttributes.addFlashAttribute("date_naissance", date_naissance);
                                    redirectAttributes.addFlashAttribute("phone", phone);
                                    return "redirect:/creer_compte";
                                }
                                else {
                                    System.out.println("email: " + personneOfContrat.getEmail());
                                    user1.setEmail(user.getEmail());
                                }
                            }
                            if (personneOfContrat.getAddress() != null) {
                                System.out.println("adresse: " + personneOfContrat.getAddress());
                                user1.setAddress(personneOfContrat.getAddress());
                            }
                            String identifiant = getRef(personneOfContrat);
                            System.out.println("identifiant =>"+identifiant);
                            User utilisateur = userRepository.findFirstByUsername(identifiant);
                            if (utilisateur != null) {
                                redirectAttributes.addFlashAttribute("success", false);
                                redirectAttributes.addFlashAttribute("title", "Erreur");
                                redirectAttributes.addFlashAttribute("message", "Cette police est deja utilisée par un autre client");
                                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
                                redirectAttributes.addFlashAttribute("user", user);
                                redirectAttributes.addFlashAttribute("police", police);
                                redirectAttributes.addFlashAttribute("date_naissance", date_naissance);
                                redirectAttributes.addFlashAttribute("phone", phone);
                                System.out.println("cette police est deja utilisée par un autre client");
                                return "redirect:/creer_compte";

                            }
                            else {
                                System.out.println("envoie de l identifiant pas sms");
                                user1.setUsername(identifiant);
                                user1.setPasswordConfirmation(user.getPasswordConfirmation());
                                //    user1.setRoles(Collections.singleton(roleRepository.findByRole("CLIENT")));
                                userRepository.save(user1);
                                String contenu = "Votre identifiant est :" + identifiant + " ";
                                String numero = "";
                                // SmsProvider.SendSmsViaInfoBip("Sanlam vie", contenu, String.format("225%s", user.getPhone()));
                                if (Utility.isLocal(user.getPhone()) != null){
                                    numero = Utility.isLocal(user.getPhone());
                                }else {
                                    numero =  user.getPhone();
                                }

                                HttpSms httpSms = new HttpSms.Builder("Sanlam vie",numero,contenu,"UTF-8").build();
                                smsHelper.sendTo(httpSms);

                                redirectAttributes.addFlashAttribute("success", true);
                                redirectAttributes.addFlashAttribute("info", true);
                                redirectAttributes.addFlashAttribute("title", "Info");
                                redirectAttributes.addFlashAttribute("message", "Saisissez l'identifiant recu pas sms afin de finaliser votre inscription");
                                return "redirect:/activation";

                            }
                        }
                        else {
                            System.out.println("aucun souscripteur pour cette date de naissance");
                            redirectAttributes.addFlashAttribute("success", false);
                            redirectAttributes.addFlashAttribute("title", "Erreur");
                            redirectAttributes.addFlashAttribute("message", "Veuillez verifier votre date de naissance (jj/mm/aaaa)");
                            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
                            redirectAttributes.addFlashAttribute("user", user);
                            redirectAttributes.addFlashAttribute("police", police);
                            redirectAttributes.addFlashAttribute("date_naissance", date_naissance);
                            redirectAttributes.addFlashAttribute("phone", phone);
                            return "redirect:/creer_compte";
                        }
                    }
                    else {
                        System.out.println("aucun souscripteur ou assuré pour ce contrat");
                        redirectAttributes.addFlashAttribute("success", false);
                        redirectAttributes.addFlashAttribute("title", "Erreur");
                        redirectAttributes.addFlashAttribute("message", "Aucun souscripteur ou Assuré");
                        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
                        redirectAttributes.addFlashAttribute("user", user);
                        redirectAttributes.addFlashAttribute("police", police);
                        redirectAttributes.addFlashAttribute("date_naissance", date_naissance);
                        redirectAttributes.addFlashAttribute("phone", phone);
                        return "redirect:/creer_compte";
                    }

                } else {
                    System.out.println("Aucune personne trouvée pour ce contrat");
                    redirectAttributes.addFlashAttribute("success", false);
                    redirectAttributes.addFlashAttribute("title", "Erreur");
                    redirectAttributes.addFlashAttribute("message", "Aucune personne trouvée pour ce contrat");
                    redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
                    redirectAttributes.addFlashAttribute("user", user);
                    redirectAttributes.addFlashAttribute("police", police);
                    redirectAttributes.addFlashAttribute("date_naissance", date_naissance);
                    redirectAttributes.addFlashAttribute("phone", phone);
                    return "redirect:/creer_compte";
                }

            }
            else {
                System.out.println("Aucun contrat trouvé pour ce numero de police");
                redirectAttributes.addFlashAttribute("success", false);
                redirectAttributes.addFlashAttribute("title", "Erreur");
                redirectAttributes.addFlashAttribute("message", "numero de police inconnu");
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
                redirectAttributes.addFlashAttribute("user", user);
                redirectAttributes.addFlashAttribute("police", police);
                redirectAttributes.addFlashAttribute("date_naissance", date_naissance);
                redirectAttributes.addFlashAttribute("phone", phone);
                return "redirect:/creer_compte";
            }

        }
    }


    @PostMapping(value = "/user/authenticate")
    public String authenticateUser(Model model, HttpServletRequest request, String username, String password, RedirectAttributes redirectAttributes) {
        System.out.println("username: " + username + " password: " + password);
        User user = userRepository.findFirstByUsername(username);
        if (user == null) {
            System.out.println("user null: ");
            Contrat contrat = contratRepository.findFirstByPolice(username);
            if (contrat != null) {
                System.out.println("contrat: " + contrat.getPolice());
                Personne pers = contrat.getSouscripteur();
                if (pers != null) {
                    System.out.println("personne: " + pers.getLastname() + "" + pers.getFirstname());
                    String identifiant = getRef(pers);
                    User utilisateur = userRepository.findFirstByUsername(identifiant);
                    if (utilisateur != null) {

                        if (new BCryptPasswordEncoder().matches(password, utilisateur.getPassword())) {
                            System.out.println("utilisateur: " + utilisateur.getFirstname() + " " + utilisateur.getLastname());
                            succesTitleMessageAttribute(redirectAttributes,true, "Connexion", succesAuth(request, utilisateur, identifiant));
                            System.out.println("sessionModel.getIdentifiant()"+sessionModel.getIdentifiant());
                            if (sessionModel.getIdentifiant() == 9000000L){
                                return "redirect:/admin/manager";
                            }else {
                                return "redirect:/portailClient/darshboard";
                            }

                        } else {
                            System.out.println("mot de passe incorrect avec contrat: ");
                            succesTitleMessageAttribute(redirectAttributes,false, "Erreur", "Mot de passe incorrect");
                            redirectAttributes.addFlashAttribute("username", username);
                            return "redirect:/login";
                        }
                    } else {
                        System.out.println("utilisateur null: ");
                        succesTitleMessageAttribute(redirectAttributes,false, "Erreur", "Vous devez vous inscrire d'abord");
                        return "redirect:/creer_compte";
                    }
                } else {
                    System.out.println("personne null: ");
                    succesTitleMessageAttribute(redirectAttributes,false, "Erreur", "Souscripteur non encore referencé");
                    return "redirect:/login";
                }
            }
            else {
                System.out.println("contrat null: ");
                succesTitleMessageAttribute(redirectAttributes,false, "Erreur", "Aucun contrat ne correspond à cette police");
                return "redirect:/login";
            }
        } else {
            if (new BCryptPasswordEncoder().matches(password, user.getPassword())) {
                System.out.println("ok fd: ");
                succesTitleMessageAttribute(redirectAttributes,true, "Connexion", succesAuth(request, user, username));
                System.out.println("1");
                if (sessionModel.getIdentifiant() == 9000000L){
                    return "redirect:/admin/manager";
                }else {
                    //System.out.println("session :"+request.getSession().getAttribute("username")+"user state :"+user.getState()+" sessionModel :"+ sessionModel.getUsername()+" "+sessionModel.getId());
                    return "redirect:/portailClient/darshboard";
                }
            } else {
                System.out.println("password incorrect: ");
                succesTitleMessageAttribute(redirectAttributes,false, "Erreur", "mot de passe incorrect");
                return "redirect:/login";
            }
        }
    }


    RedirectAttributes succesTitleMessageAttribute(RedirectAttributes redirectAttributes, boolean succes, String title, String message){
        redirectAttributes.addFlashAttribute("success", succes);
        redirectAttributes.addFlashAttribute("title", title);
        redirectAttributes.addFlashAttribute("message", message);
        return redirectAttributes;
    }

    String succesAuth(HttpServletRequest request, User user , String username){
        String civilite = user.getCivilite() == null ? " " :user.getCivilite();
        String firstname = user.getFirstname() == null ? " " :user.getFirstname();
        String lastname = user.getLastname() == null ? " " :user.getLastname();

        sessionModel.setUsername(username);
        sessionModel.setId(user.getId());
        request.getSession().setAttribute("username", username);
        user.setState(Constant.CONNECTED);
        userRepository.save(user);
        return "content de vous revoir " + civilite + " " + firstname + " " + lastname;

    }

    @PostMapping(value = "/search/desherence/contrat")
    public String contratEnDesherence(@RequestParam(value = "lastname") String nom,
                                      @RequestParam(value = "firstname") String prenom,
                                      @RequestParam(value = "date_naissance") String dateNaiss,
                                      @RequestParam(value = "phone") String telephone,
                                      @RequestParam(value = "police", required = false) String numero,
                                      RedirectAttributes redirectAttributes){
        DesherenceContrat contrat;
        String fullnameOne = String.format("%s %s",nom,prenom);
        String fullnameTwo = String.format("%s %s",prenom,nom);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        redirectAttributes.addFlashAttribute("success", true);
        redirectAttributes.addFlashAttribute("title", "Success");
        redirectAttributes.addFlashAttribute("message", "Votre contrat a été retrouvé, veuillez prendre contact avec Sanlam au (+225) 27 20 25 97 10 ou 27 20 25 97 09");

        if (numero != null && !numero.isEmpty() && contratDesherenceRepository.findByChContrat(numero) !=null ){
             contrat = contratDesherenceRepository.findByChContrat(numero);
                sendSuccesSms(telephone,Constant.contrat_desherence_message);
                sendSuccesMail(telephone, null, contrat);
        }else {
            Date dateNais = null;
            try {
                dateNais = formatter.parse(dateNaiss);
                contrat = contratDesherenceRepository.findByNomAssureAndDtNaisAssure(fullnameOne,dateNais);
                if (contrat != null){
                    sendSuccesSms(telephone,Constant.contrat_desherence_message);
                    sendSuccesMail(telephone, dateNais, contrat);
                }else {
                    contrat = contratDesherenceRepository.findByNomAssureAndDtNaisAssure(fullnameTwo,dateNais);
                    if (contrat !=null){
                        sendSuccesSms(telephone,Constant.contrat_desherence_message);
                        sendSuccesMail(telephone, dateNais, contrat);
                    }else{
                        contrat = contratDesherenceRepository.findByNomSouscAndDtNaisSouscript(fullnameOne,dateNais);
                        if (contrat !=null){
                            sendSuccesSms(telephone,Constant.contrat_desherence_message);
                            sendSuccesMail(telephone, dateNais, contrat);
                        }else{
                            contrat = contratDesherenceRepository.findByNomSouscAndDtNaisSouscript(fullnameTwo,dateNais);
                            if (contrat !=null){
                                sendSuccesSms(telephone,Constant.contrat_desherence_message);
                                sendSuccesMail(telephone, dateNais, contrat);
                                // envoi de mail
                            }else {
                                // Introuvable
                                redirectAttributes.addFlashAttribute("police", numero);
                                redirectAttributes.addFlashAttribute("phone", telephone);
                                redirectAttributes.addFlashAttribute("date_naissance", dateNaiss);
                                redirectAttributes.addFlashAttribute("firstname", prenom);
                                redirectAttributes.addFlashAttribute("lastname", nom);
                                redirectAttributes.addFlashAttribute("success", false);
                                redirectAttributes.addFlashAttribute("title", "Erreur");
                                redirectAttributes.addFlashAttribute("message", "Aucun contrat n'a été trouvé ");

                            }
                        }
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }


        return "redirect:/desherence";

    }

    public void sendSuccesSms(String telephone,String message){
        HttpSms httpSms = new HttpSms.Builder("Sanlam vie",String.format("225%s",telephone),message,"UTF-8").build();
        smsHelper.sendTo(httpSms);
    }
    public void sendSuccesMail(String phone, Date dateNais,DesherenceContrat desherenceContrat){
        try {
            Context context = new Context();
            context.setVariable("phone",phone);
            context.setVariable("dateNais",dateNais);
            context.setVariable("contrat",desherenceContrat);
            Future<String> content = mailTemplateHandler.build("mailTemplates/contrat-en-desherence-ou-echus",context);
            //javaMailSenderHandler.send(Constant.mail_dev,"Contrat en déshérence ou échus",content.get().toString(),true);
            //javaMailSenderHandler.send(Constant.mail_prod,"Contrat en déshérence ou échus ",content.get().toString(),true);
            javaMailSenderHandler.send(Constant.mail_dev,"Contrat en déshérence ou échus ",content.get().toString(),true);
            javaMailSenderHandler.send(Constant.mail_prod,"Contrat en déshérence ou échus ",content.get().toString(),true);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
