package com.digital.service.bootstrap;

import com.digital.model.Branche;
import com.digital.model.Objets;
import com.digital.repository.BrancheRepository;
import com.digital.repository.ObjetsRepository;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class BrancheBootstrap {
    public void seed(String filePath, BrancheRepository brancheRepository){
        if (brancheRepository.count() ==0){
            try {
                List<Branche> beans = new CsvToBeanBuilder(new FileReader(filePath)).withType(Branche.class).build().parse();
                beans.forEach(brancheRepository::save);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
