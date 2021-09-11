package com.digital.service.bootstrap;

import com.digital.model.Role;
import com.digital.repository.RoleRepository;

public class RoleBootstrap {

    public void seek(RoleRepository roleRepository){
        if (roleRepository.count() == 0){
            Role role1 = roleRepository.save(new Role("ADMIN"));
            Role role2 = roleRepository.save(new Role("CLIENT"));
            Role role3 = roleRepository.save(new Role("MANAGER"));
        }
    }
}
