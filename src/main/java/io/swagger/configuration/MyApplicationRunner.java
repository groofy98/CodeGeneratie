package io.swagger.configuration;

import io.swagger.dao.BalanceRepository;
import io.swagger.dao.TransactionRepository;
import io.swagger.dao.AccountRepository;
import io.swagger.model.Account;
import io.swagger.model.Balance;
import io.swagger.model.Transaction;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

import java.util.Arrays;
import java.util.List;

@Component
@ConditionalOnProperty(prefix = "bank.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
@Transactional
public class MyApplicationRunner implements ApplicationRunner {

    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;
    private BalanceRepository balanceRepository;

    public MyApplicationRunner(TransactionRepository transactionRepository, AccountRepository accountRepository, BalanceRepository balanceRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.balanceRepository = balanceRepository;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        List<Transaction> transactions = Arrays.asList(
                new Transaction((double) 77777, "NL42INHO0000000002", "NL42INHO0000000003", (long) 5, Transaction.TransactionTypeEnum.TRANSFER),
                    new Transaction((double) 98777, "NL42INHO0000000003", "NL42INHO0000000002", (long) 5, Transaction.TransactionTypeEnum.TRANSFER));
        List<Account> accounts = Arrays.asList(
                new Account("NL42INHO0000000002", (long) -100, (long) 123456789, Account.AccountTypeEnum.CURRENT, true),
                new Account("NL42INHO0000000003", (long) -0, (long) 123456789, Account.AccountTypeEnum.SAVING, true),
                    new Account("NL42INHO0000000004", (long) 22.50, (long) 123456789, Account.AccountTypeEnum.CURRENT, true));

        List<Balance> balances = Arrays.asList(
            new Balance("NL42INHO0000000002", new BigDecimal("11.94")),
            new Balance("NL42INHO0000000003", new BigDecimal("2394.87")),
            new Balance("NL42INHO0000000004", new BigDecimal("900123.32"))
        );
        transactions.forEach(transactionRepository::save);
        accounts.forEach((accountRepository::save));
        balances.forEach((balanceRepository::save));
        transactionRepository.findAll().forEach(System.out::println);
        accountRepository.findAll().forEach(System.out::println);
        balanceRepository.findAll().forEach(System.out::println);
    }
}
