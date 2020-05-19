package io.swagger.configuration;

import io.swagger.dao.TransactionRepository;
import io.swagger.dao.AccountRepository;
import io.swagger.model.Account;
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
    private AccountRepository accountRepository;

    public MyApplicationRunner(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        List<Transaction> transactions = Arrays.asList(new Transaction((double) 77777, "sjors", "patrick", (long) 5, Transaction.TransactionTypeEnum.TRANSFER), new Transaction((double) 98777, "Yolo", "Swek", (long) 5, Transaction.TransactionTypeEnum.TRANSFER));
        List<Account> accounts = Arrays.asList(
                new Account("NL42 INHO 0000 0000 02", (long) -100, (long) 123456789, Account.AccountTypeEnum.CURRENT, true),
                new Account("NL42 INHO 0000 0000 03", (long) -0, (long) 123456789, Account.AccountTypeEnum.SAVING, true),
                new Account("NL42 INHO 0000 0000 04", (long) 22.50, (long) 123456789, Account.AccountTypeEnum.CURRENT, true));
        transactions.forEach(transactionRepository::save);
        accounts.forEach((accountRepository::save));
        transactionRepository.findAll().forEach(System.out::println);
        accountRepository.findAll().forEach(System.out::println);
    }
}
