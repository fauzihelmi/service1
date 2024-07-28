package com.data.test.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionDto {

    private String transactionFromAccount;
    private String transactionAmount;
    private String transactionDate;
    private String transactionType;
}
