package com.digital.service.bootstrap;

import com.digital.model.Objets;
import com.digital.model.Profils;
import com.digital.repository.ObjetsRepository;
import com.digital.repository.ProfilsRepository;
import com.digital.repository.TypeProfilsRepository;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class ProfilBootstrap {
    public void seed(String filePath, ProfilsRepository profilsRepository, TypeProfilsRepository typeProfilsRepository){
        if (profilsRepository.count() ==0){
            try {
                List<Profils> beans = new CsvToBeanBuilder(new FileReader(filePath)).withType(Profils.class).build().parse();
                beans.forEach(profils -> {
                    profils.setTypeProfils(typeProfilsRepository.findOne(profils.getProfilTypeId()));
                    profilsRepository.save(profils);
                });
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
