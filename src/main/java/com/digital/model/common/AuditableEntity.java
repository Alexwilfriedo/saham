package com.digital.model.common;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author babacoul
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity<U> {

  @Column(name = "created_by")
  @CreatedBy
  protected U createdBy;

  @Column(name = "updated_by")
  @LastModifiedBy
  protected U updatedBy;

  @Column(name = "created_date", nullable = false, updatable = false)
  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  protected Date createdDate;

  @Column(name = "updated_date")
  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  protected Date updatedDate;
}
