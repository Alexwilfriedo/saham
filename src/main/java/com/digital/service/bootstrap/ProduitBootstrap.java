package com.digital.service.bootstrap;

import com.digital.model.Objets;
import com.digital.model.Produit;
import com.digital.repository.BrancheRepository;
import com.digital.repository.ObjetsRepository;
import com.digital.repository.ProduitRepository;
import com.digital.repository.TypesProduitRepository;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class ProduitBootstrap {
    public void seed(String filePath, ProduitRepository produitRepository, BrancheRepository brancheRepository, TypesProduitRepository typesProduitRepository){
        if (produitRepository.count() ==0){
            try {
                List<Produit> beans = new CsvToBeanBuilder(new FileReader(filePath)).withType(Produit.class).build().parse();
                beans.forEach(produit -> {
                    produit.setBranche(brancheRepository.getOne(produit.getRefbranche()));
                    produit.setTypesProduit(typesProduitRepository.findOne(produit.getTypeProduit()));
                    produitRepository.save(produit);
                });
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
