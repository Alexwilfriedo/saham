package com.digital.service.bootstrap;

import com.digital.model.StatutContrat;
import com.digital.repository.StatutContratRepository;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class StatutContratBootstrap {
    public void seed(String filePath, StatutContratRepository statutContratRepository){
        if (statutContratRepository.count() ==0){
            try {
                List<StatutContrat> beans = new CsvToBeanBuilder(new FileReader(filePath)).withType(StatutContrat.class).build().parse();
                beans.forEach(statutContratRepository::save);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
