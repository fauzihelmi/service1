package com.data.test.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TRANSACTION_TRANSFER")
public class TransactionTransfer {

    @Id
    @GeneratedValue
    @Column(name="TRS_ID")
    private int transactionId;

    @Column(name="TRS_TOACCOUNT")
    private String transactionToAccount;

    @Column(name="TRS_STATUS")
    private String transactionStatus;
}
