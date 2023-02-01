package westernacher.task.service;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import westernacher.task.dto.AccountDTO;
import westernacher.task.mapper.AccountMapper;
import westernacher.task.model.Account;
import westernacher.task.repository.AccountRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AccountServiceImplTest {

    @SpyBean
    private AccountMapper accountMapper;

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    private final Long accountId = 1L;
    private final Account accountInDb = Account.builder().id(accountId).firstName("Original").build();

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAccountList() {
        accountService.getAccountList();
        Mockito.verify(accountRepository).findAll();
    }

    @Test
    void deleteAccount() {
        accountService.deleteAccount(accountId);
        Mockito.verify(accountRepository).deleteAccountById(accountId);
    }

    @Test
    void editAccount() {
        AccountDTO changeAccount = AccountDTO.builder().id(accountId).firstName("Changed").build();
        Mockito.when(accountRepository.getAccountById(accountId)).thenReturn(Optional.of(accountInDb));

        accountService.editAccount(accountId, changeAccount);

        Mockito.verify(accountMapper).updateAccountFromDto(changeAccount, accountInDb);
        Mockito.verify(accountRepository).save(Mockito.any(Account.class));
    }
}