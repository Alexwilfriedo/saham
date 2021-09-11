package com.digital.service.bootstrap;

import com.digital.model.Objets;
import com.digital.repository.ObjetsRepository;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class ObjetBootstrap {
    public void seed(String filePath, ObjetsRepository objetsRepository){
        if (objetsRepository.count() ==0){
            try {
                List<Objets> beans = new CsvToBeanBuilder(new FileReader(filePath)).withType(Objets.class).build().parse();
                beans.forEach(objetsRepository::save);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
