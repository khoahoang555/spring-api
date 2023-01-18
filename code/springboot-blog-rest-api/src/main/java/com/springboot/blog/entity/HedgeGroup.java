package com.springboot.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_hedge_group", uniqueConstraints = {@UniqueConstraint(columnNames = {"organization_id", "name"})})
public class HedgeGroup extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();

    @Column(name = "org_member_id", nullable = true)
    private UUID orgMemberId;

    @Column(name = "organization_id", nullable = true)
    private UUID organizationId;

    @Column(name="name", length = 64, nullable = false)
    private String name;

    @OneToMany(mappedBy = "hedgeGroup", fetch = FetchType.LAZY)
    @org.hibernate.annotations.OrderBy(clause = "createdAt ASC")
    private Set<Hedge> hedges;
}
