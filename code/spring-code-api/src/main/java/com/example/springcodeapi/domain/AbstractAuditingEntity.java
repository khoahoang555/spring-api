package com.example.springcodeapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditingEntity implements Serializable {
    @CreatedBy
    @Column(name = "created_by", updatable = false)
    @JsonIgnore
    private UUID createdBy;

    @CreatedDate
    @Column(name = "create_at", updatable = false)
    @JsonIgnore
    private Instant createAt = Instant.now();

    @LastModifiedBy
    @Column(name = "modified_by")
    @JsonIgnore
    private UUID modifiedBy;

    @LastModifiedDate
    @Column(name = "modified_at")
    @JsonIgnore
    private Instant modifiedAt;

    public UUID getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public UUID getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(UUID modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
