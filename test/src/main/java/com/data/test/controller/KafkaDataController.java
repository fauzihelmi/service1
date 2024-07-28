package com.data.test.controller;

import com.data.test.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaDataController {

    @Autowired
    TransactionService transactionService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @PostMapping("/publish-transaction")
    public ResponseEntity<String> transaction(@RequestParam("customerName") String customerName,
                                              @RequestParam("date") Date date) {
        String transactionStatus = transactionService.getTransactions(customerName, date);
        String reason;
        if (transactionStatus.equals("200")) {
            reason = "Data sent to kafka topic";
        } else {
            reason = "Data empty sent to kafka topic";
        }
        return new ResponseEntity<>(reason, HttpStatus.OK);
    }
}
