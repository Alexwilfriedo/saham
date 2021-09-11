package com.digital.service.bootstrap;

import com.digital.model.StatutQuitance;
import com.digital.repository.StatutQuitanceRepository;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class StatutQuittanceBootstrap {
    public void seed(String filePath, StatutQuitanceRepository statutQuitanceRepository){
        if (statutQuitanceRepository.count() ==0){
            try {
                List<StatutQuitance> beans = new CsvToBeanBuilder(new FileReader(filePath)).withType(StatutQuitance.class).build().parse();
                beans.forEach(statutQuitanceRepository::save);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
