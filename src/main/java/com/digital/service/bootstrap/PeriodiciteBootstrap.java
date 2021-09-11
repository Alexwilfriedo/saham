package com.digital.service.bootstrap;

import com.digital.model.Objets;
import com.digital.model.Periodicite;
import com.digital.repository.ObjetsRepository;
import com.digital.repository.PeriodiciteRepository;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class PeriodiciteBootstrap {
    public void seed(String filePath, PeriodiciteRepository periodiciteRepository){
        if (periodiciteRepository.count() ==0){
            try {
                List<Periodicite> beans = new CsvToBeanBuilder(new FileReader(filePath)).withType(Periodicite.class).build().parse();
                beans.forEach(periodiciteRepository::save);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
