package com.data.test.repository;

import com.data.test.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    @Query(
            value = "SELECT trx.\"TRS_FROM_ACCOUNT\" AS transactionFromAccount, \n" +
                    "\ttrx.\"TRS_AMOUNT\" AS transactionAmount , \n" +
                    "\ttrx.\"TRS_DATE\" AS transactionDate ,\n" +
                    "\tCASE \n" +
                    "\tWHEN trx.\"TRS_TYPE\" = 'DB' THEN 'Debit'\n" +
                    "\tELSE 'Credit'\n" +
                    "\tEND AS transactionType\n" +
                    "FROM \"TRANSACTION\" trx\n" +
                    "LEFT JOIN \"ACCOUNT\" act ON trx.\"TRS_FROM_ACCOUNT\" = act.\"ACC_NUMBER\"\n" +
                    "LEFT JOIN \"CUSTOMER\" cus ON act.\"ACC_OWNER\" = cus.\"CUST_ID\"\n" +
                    "WHERE concat(cus.\"CUST_FIRSTNAME\", ' ', cus.\"CUST_LASTNAME\") = :custName\n" +
                    "\tAND act.\"ACC_NUMBER\" = '2'\n" +
                    "\tAND trx.\"TRS_DATE\" = :date",
            nativeQuery = true
    )
    List<Object[]> findTransactionByDate(@Param("custName") String custName,@Param("date") Date date);
}
