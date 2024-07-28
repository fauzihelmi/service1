package com.data.test.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue
    @Column(name="CUST_ID")
    private int customerId;

    @Column(name="CUST_FIRSTNAME")
    private String firstName;

    @Column(name="CUST_LASTNAME")
    private String lastName;

    @Column(name="CUST_BIRTHDATE")
    private Date birthDate;

    @Column(name="CUST_GENDER")
    private String gender;

    @Column(name="CUST_ADDRESS")
    private String address;

    @Column(name="CUST_CITY")
    private String city;

    @Column(name="CUST_POSTCODE")
    private String postCode;
}
