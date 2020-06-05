//package io.swagger.api;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.swagger.api.AccountApiController;
//import io.swagger.dao.AccountRepository;
//import io.swagger.dao.TransactionRepository;
//import io.swagger.model.Account;
//import io.swagger.model.Transaction;
//import io.swagger.service.AccountService;
//import io.swagger.service.BalanceService;
//import io.swagger.service.TransactionService;
//import org.junit.Before;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.constraints.AssertTrue;
//import java.math.BigDecimal;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class AccountControllerTest {
//
//    @Autowired
//    AccountApiController accountApiController;
//
//    @MockBean
//    AccountRepository accountRepository;
//
//    @BeforeEach
//    public void setUp() {
//    }
//
//    @Test
//    public void GetNullWhenAccountDoesNotExist(){
//        ResponseEntity<Void> httpStatus = accountApiController.deactivateAccount("invalid_IBAN");
//        assertSame(httpStatus, HttpStatus.INTERNAL_SERVER_ERROR);
//        System.out.println(httpStatus);
//    }
//}
