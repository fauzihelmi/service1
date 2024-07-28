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
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;

    @Column(name="ACC_NUMBER")
    private String accountNumber;

    @Column(name="ACC_OWNER")
    private String accountOwner;

    @Column(name="ACC_DATE_CREATED")
    private Date createdAt;

    @Column(name="ACC_BALLANCE")
    private BigDecimal balance;

}
