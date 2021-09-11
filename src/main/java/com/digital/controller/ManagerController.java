package com.digital.controller;


import com.digital.model.SessionModel;
import com.digital.model.User;
import com.digital.repository.MessageRepository;
import com.digital.repository.UserRepository;
import com.digital.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class ManagerController {
    @Autowired
    private UserRepository userRepository;
    @Autowired private MessageRepository messageRepository;
    @Autowired SessionModel session;

    @GetMapping("/admin/manager")
    public String index(Model model, @ModelAttribute("userMesg") User user) {
        User personne = userRepository.findFirstByUsername(session.getUsername());
       // List<User> usersConnected = userRepository.findAllByUsernameIsNotOrderByState(Constant.USERNAME_MANAGER_SAHAM);
        //List<User> usersConnected = userRepository.findAllByStateAndUsernameIsNot(Constant.CONNECTED,Constant.USERNAME_MANAGER_SAHAM);
        List<User> usersConnected = userRepository.findAllByStateAndUsernameIsNotOrderByMessageNonLuAsc(Constant.CONNECTED,Constant.USERNAME_MANAGER_SAHAM);
        if (user != null ){
            System.out.println("user selected :"+user.getLastname()+" prenom :"+user.getFirstname());
        }
        model.addAttribute("expediteurUsername",session.getUsername());
        model.addAttribute("connected",usersConnected);
        model.addAttribute("nbreMessage",messageRepository.findAllByState(false).size());
        model.addAttribute("nbreInscrit",userRepository.findAll().size());
        model.addAttribute("nbreConnected",userRepository.findAllByState(Constant.CONNECTED).size());
        model.addAttribute("perso", personne);
        return "manager/index";
    }

/*    @RequestMapping(value = "/user-message", method = RequestMethod.POST,produces = "text/html; charset=UTF-8")
    public String getUserMessage(Long id, Model model,RedirectAttributes redirectAttributes) {
        System.out.println("id =>"+id);
        List<Message> messages = null;
        List<User> usersConnected = userRepository.findAllByState(Constant.CONNECTED);
        if (id >0 ){
            User user = userRepository.findOne(id);
                if (user != null ){
                    System.out.println("user selected :"+user.getLastname()+" prenom :"+user.getFirstname());
                    messages = (List<Message>) messageRepository.findByExpediteurOrderByIdAsc(user);
                     model.addAttribute("userMesg",user);
                }
            }
        model.addAttribute("tchat_messages",messages);
        model.addAttribute("sessionId",session.getUsername());
        model.addAttribute("connected",usersConnected);
        return "manager/vue-message-manager";
    }*/
}
