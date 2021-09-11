package com.digital.repository;

import com.digital.model.Bancassurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BancassuranceRepository extends JpaRepository<Bancassurance,Long>{
}
