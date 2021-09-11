package com.digital.repository;

import com.digital.model.QuittanceMatrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuittanceMatriceRepository extends JpaRepository<QuittanceMatrice,Long>{

    QuittanceMatrice findByCodeContrat(String code);
    QuittanceMatrice findFirstByCodeContrat(String code);
}
