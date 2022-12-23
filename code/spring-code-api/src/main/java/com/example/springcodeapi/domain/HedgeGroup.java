package com.example.springcodeapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_hedge_group")
public class HedgeGroup extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();

    @Column(length = 64, nullable = false)
    private String name;

}
