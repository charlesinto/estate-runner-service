package com.ireveal.EstateRunner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ireveal.EstateRunner.enums.EntityStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.model
 * @Date 03/02/2025
 */

@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    private EntityStatus status = EntityStatus.ACTIVE;

    @CreatedDate
    private Timestamp createdOn;

    @LastModifiedDate
    private Timestamp lastModifiedOn;

    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;

    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity baseModel = (BaseEntity) o;

        return new EqualsBuilder()
                .append(id, baseModel.id)
                .append(status, baseModel.status)
                .append(createdOn, baseModel.createdOn)
                .append(lastModifiedOn, baseModel.lastModifiedOn)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(status)
                .append(createdOn)
                .append(lastModifiedOn)
                .toHashCode();
    }
}
