package com.data.test.service;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface TransactionService {

    String getTransactions(String customerName, Date date);
}
