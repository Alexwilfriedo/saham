package com.digital.service.bootstrap;

import com.digital.model.TypesProduit;
import com.digital.repository.TypesProduitRepository;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class TypeProduitBootstrap {
    public void seed(String filePath, TypesProduitRepository typesProduitRepository){
        if (typesProduitRepository.count() ==0){
            try {
                List<TypesProduit> beans = new CsvToBeanBuilder(new FileReader(filePath)).withType(TypesProduit.class).build().parse();
                beans.forEach(typesProduitRepository::save);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
