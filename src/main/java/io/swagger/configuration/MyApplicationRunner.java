package io.swagger.configuration;

import io.swagger.dao.TransactionRepository;
import io.swagger.model.Transaction;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.threeten.bp.OffsetDateTime;

import java.util.Arrays;
import java.util.List;

@Component
@ConditionalOnProperty(prefix = "bank.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
@Transactional
public class MyApplicationRunner implements ApplicationRunner {

    private TransactionRepository transactionRepository;

    public MyApplicationRunner(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        List<Transaction> transactions = Arrays.asList(new Transaction(OffsetDateTime.now(), (double)77777, "sjors", "patrick", (long)5, Transaction.TransactionTypeEnum.TRANSFER), new Transaction(OffsetDateTime.now(), (double)77777, "Yolo", "Swek", (long)5, Transaction.TransactionTypeEnum.TRANSFER));
        transactions.forEach(transactionRepository::save);
        transactionRepository.findAll().forEach(System.out::println);
    }
}
