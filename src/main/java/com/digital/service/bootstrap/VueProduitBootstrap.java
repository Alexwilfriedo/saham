package com.digital.service.bootstrap;

import com.digital.model.Branche;
import com.digital.model.Produit;
import com.digital.model.TypesProduit;
import com.digital.model.VueProduit;
import com.digital.repository.BrancheRepository;
import com.digital.repository.TypesProduitRepository;
import com.digital.repository.VueProduitRepository;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class VueProduitBootstrap {
    public void seed(
            String filePath,
            VueProduitRepository vueProduitRepository,
            BrancheRepository brancheRepository,
            TypesProduitRepository typesProduitRepository
    ){
        if (vueProduitRepository.count() == 0L){
            try {
                List<Produit> beans = new CsvToBeanBuilder(new FileReader(filePath)).withType(Produit.class).build().parse();
                beans.forEach(produit -> {
                    Branche branche = brancheRepository.getOne(produit.getRefbranche());
                    TypesProduit typesProduit = typesProduitRepository.findOne(produit.getTypeProduit());
                    vueProduitRepository.save(new VueProduit(
                            produit.getCode(),
                            produit.getNomProduit(),
                            branche.getId(),
                            branche.getBranche(),
                            typesProduit.getId(),
                            typesProduit.getType()
                    ));
                });
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
