package com.data.test.service;

import com.data.test.dto.TransactionDto;
import com.data.test.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionrepository;

    @Value("topic1")
    private String topicJsonName;

    private final KafkaTemplate<String, TransactionDto> kafkaTemplate;

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);

    public TransactionServiceImpl(KafkaTemplate<String, TransactionDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Cacheable(value = "transactionsCache", key = "#customerName.concat('-').concat(#date.toString())")
    public String getTransactions(String customerName, Date date) {
        List<Object[]> transactions = transactionrepository.findTransactionByDate(customerName, date);
        List<TransactionDto> transactionDtos = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Object[] row : transactions) {
            TransactionDto transactionDto = new TransactionDto();
            transactionDto.setTransactionFromAccount(row[0] != null ? row[0].toString() : "");
            transactionDto.setTransactionAmount(row[1] != null ? row[1].toString() : "");
            transactionDto.setTransactionDate(row[2] != null ? sdf.format(row[2]) : "");
            transactionDto.setTransactionType(row[3] != null ? row[3].toString() : "");
            transactionDtos.add(transactionDto);
        }

        Message<List<TransactionDto>> message = MessageBuilder
                .withPayload(transactionDtos)
                .setHeader(KafkaHeaders.TOPIC, topicJsonName)
                .build();

        try {
            kafkaTemplate.send(message).get();
            logger.info("Sent transactions to Kafka topic {}", topicJsonName);
        } catch (Exception e) {
            logger.error("Failed to send transactions to Kafka", e);
            return "500";
        }

        return !transactionDtos.isEmpty() ? "200" : "204";
    }
}
