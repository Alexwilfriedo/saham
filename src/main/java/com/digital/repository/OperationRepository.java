package com.digital.repository;

import com.digital.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation,Long>{

    public List<Operation> findAllByUsername(String username);
}
