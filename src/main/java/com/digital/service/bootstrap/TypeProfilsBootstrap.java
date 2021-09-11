package com.digital.service.bootstrap;

import com.digital.model.TypeProfils;
import com.digital.repository.TypeProfilsRepository;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class TypeProfilsBootstrap {
    public void seed(String filePath, TypeProfilsRepository typeProfilsRepository){
        if (typeProfilsRepository.count() ==0){
            try {
                List<TypeProfils> beans = new CsvToBeanBuilder(new FileReader(filePath)).withType(TypeProfils.class).build().parse();
                beans.forEach(typeProfilsRepository::save);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
