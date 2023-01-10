package com.springboot.blog.entity;

import com.smart.exchange.orderservice.constant.Choices;
import com.smart.exchange.sharedlibrary.domain.AbstractAuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_hedge")
public class Hedge extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue
    private UUID id = UUID.randomUUID();

    @Column(name="hedge_id", nullable = true)
    private Long hedgeId;

    @Column(name = "org_member_id", nullable = true)
    private UUID orgMemberId;

    @Column(name = "organization_id", nullable = true)
    private UUID organizationId;

    @Column(name = "hedge_name", length = 128, nullable = false)
    private String hedgeName;

    @Column(name = "period", length = 64, nullable = true)
    private String period;

    @Column(name = "note", length = 256, nullable = true)
    private String note;

    @Column(name = "status", length = 64, nullable = true)
    @Enumerated(EnumType.STRING)
    private Choices.HEDGE_ORDER_STATUS status;

    @Column(name = "currency_pair", length = 64, nullable = true)
    private String currencyPair;

    @Column(name = "buying_and_selling", length = 64, nullable = true)
    private String buyingAndSelling;

    @Column(name = "transaction_amount", nullable = true)
    private Long transactionAmount;

    @Column(name = "percentage", nullable = true)
    private Integer percentage;

    @Column(name = "amount", nullable = true)
    private Long amount;

    @Column(name = "start_date", nullable = true)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = true)
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hedge_group_id")
    private HedgeGroup hedgeGroup;
}
