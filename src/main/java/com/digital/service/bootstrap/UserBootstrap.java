package com.digital.service.bootstrap;

import com.digital.model.User;
import com.digital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserBootstrap {
    @Autowired PasswordEncoder passwordEncoder;

    public void seek(UserRepository userRepository){
        List<User> userList = new ArrayList<>();
        userList.add(new User("13000026", new BCryptPasswordEncoder().encode("G020R10517"), new Date()));
        userList.add(new User("13000028", new BCryptPasswordEncoder().encode("G010R10315"), new Date()));
        userList.add(new User("13000029", new BCryptPasswordEncoder().encode("G011R10118"), new Date()));
        userList.add(new User("13000030", new BCryptPasswordEncoder().encode("G012R10218"), new Date()));
        userList.add(new User("13000031", new BCryptPasswordEncoder().encode("G018R10618"), new Date()));
        userList.add(new User("13000032", new BCryptPasswordEncoder().encode("G013R10318"), new Date()));
        userList.add(new User("13000033", new BCryptPasswordEncoder().encode("G015R10418"), new Date()));
        userList.add(new User("13000034", new BCryptPasswordEncoder().encode("G007R10219"), new Date()));
        userList.add(new User("13000035", new BCryptPasswordEncoder().encode("G010R10319"), new Date()));
        userList.add(new User("13000036", new BCryptPasswordEncoder().encode("G019R10718"), new Date()));
        userList.add(new User("13000037", new BCryptPasswordEncoder().encode("G013R10318"), new Date()));
        userList.add(new User("13000050", new BCryptPasswordEncoder().encode("GR00941107"), new Date()));
        userList.add(new User("13000027", new BCryptPasswordEncoder().encode("ORANGECI"), new Date()));

        userList.forEach(user -> {
            if (userRepository.findFirstByUsername(user.getUsername()) == null) userRepository.save(user);
        });
    }
}
