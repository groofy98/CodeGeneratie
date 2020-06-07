package io.swagger.configuration;

import io.swagger.dao.BalanceRepository;
import io.swagger.dao.TransactionRepository;
import io.swagger.dao.AccountRepository;
import io.swagger.dao.UserRepository;
import io.swagger.model.Account;
import io.swagger.model.Balance;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.spec.PSource;
import java.math.BigDecimal;

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

@Component
@ConditionalOnProperty(prefix = "bank.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
@Transactional
public class MyApplicationRunner implements ApplicationRunner {

    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;
    private BalanceRepository balanceRepository;
    private UserRepository userRepository;

    public MyApplicationRunner(TransactionRepository transactionRepository, AccountRepository accountRepository, BalanceRepository balanceRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.balanceRepository = balanceRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(BigDecimal.valueOf(777), "NL42INHO0000000002", "NL42INHO0000000003", (long) 5, Transaction.TransactionTypeEnum.TRANSFER),
                new Transaction(BigDecimal.valueOf(987), "NL42INHO0000000003", "NL42INHO0000000002", (long) 5, Transaction.TransactionTypeEnum.TRANSFER));
        List<Account> accounts = Arrays.asList(
                new Account("NL42INHO0000000002", (long) -100, (long) 100001, Account.AccountTypeEnum.CURRENT, true),
                new Account("NL42INHO0000000003", (long) 0, (long) 100001, Account.AccountTypeEnum.SAVING, true),
                new Account("NL42INHO0000000004", (long) 22.50, (long) 100003, Account.AccountTypeEnum.CURRENT, true),
                new Account("NL42INHO0000000005", (long) 0, (long) 100003, Account.AccountTypeEnum.CURRENT, true));

        List<Balance> balances = Arrays.asList(
            new Balance("NL42INHO0000000002", new BigDecimal("11.94")),
            new Balance("NL42INHO0000000003", new BigDecimal("2394.87")),
            new Balance("NL42INHO0000000004", new BigDecimal("900123.32"))
        );

        List<User> users = Arrays.asList(
                new User("John", "Doe", "Johnnyboy@example.com", "Johnny69", "123",
                        new SimpleDateFormat("yyyy-MM-dd").parse("2000-02-22"),false, true, true),
                new User("Vera", "Gene", "Veragene@example.com", "Vera", "123",
                        new SimpleDateFormat("yyyy-MM-dd").parse("2000-02-22"),true, false, true),
                new User("Selena", "Cargo", "Selenacargo@example.com", "Selena", "123",
                        new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-22"),true, true, true) );

        transactions.forEach(transactionRepository::save);
        accounts.forEach((accountRepository::save));
        balances.forEach((balanceRepository::save));
        users.forEach((userRepository::save));
        transactionRepository.findAll().forEach(System.out::println);
        accountRepository.findAll().forEach(System.out::println);
        balanceRepository.findAll().forEach(System.out::println);
        userRepository.findAll().forEach(System.out::println);
    }
}
