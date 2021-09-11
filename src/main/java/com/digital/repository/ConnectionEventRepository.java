package com.digital.repository;

import com.digital.model.ConnectionEvent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author babacoul
 */
public interface ConnectionEventRepository extends JpaRepository<ConnectionEvent, Long> {

}
