package com.data.test.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue
    @Column(name="TRS_ID")
    private int transactionId;

    @Column(name="TRS_FROM_ACCOUNT")
    private String fromAccount;

    @Column(name="TRS_DATE")
    private Date transactionDate;

    @Column(name="TRS_AMOUNT")
    private BigDecimal transactionAmount;

    @Column(name="TRS_TYPE")
    private String transactionType;

}
