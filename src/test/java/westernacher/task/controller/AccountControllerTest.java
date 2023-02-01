package westernacher.task.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;

import westernacher.task.dto.AccountDTO;
import westernacher.task.mapper.AccountMapper;
import westernacher.task.model.Account;
import westernacher.task.model.Sorting;
import westernacher.task.service.AccountService;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private AccountMapper accountMapper;
    @MockBean
    private AccountService accountService;

    private final Account account1 = Account.builder().firstName("test2").build();
    private final Account account2 = Account.builder().firstName("bath").build();
    private final Account account3 = Account.builder().firstName("air").build();

    @Test
    void getAllAccounts() throws Exception {

        Sorting sorting = new Sorting(Sorting.SortingType.FIRSTNAME);

        List<Account> resultList = new ArrayList<>();
        resultList.add(account3);
        resultList.add(account2);
        resultList.add(account1);

        when(accountService.getAccountList()).thenReturn(List.of(account1, account2, account3));

        mockMvc.perform(get("/accounts/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(sorting)))
                .andExpect(status().isOk())
                .andExpect(content().json(new Gson().toJson(resultList)));

        verify(accountService).getAccountList();

    }

    @Test
    void deleteAccount() throws Exception {
        mockMvc.perform(delete("/accounts/delete/1"))
                .andExpect(status().isOk());

        verify(accountService).deleteAccount(1L);
    }

    @Test
    void editAccount() throws Exception {
        AccountDTO changeAccount = AccountDTO.builder().id(1L).firstName("changedName").build();
        String changeRequest = new Gson().toJson(changeAccount);

        mockMvc.perform(put("/accounts/edit/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(changeRequest))
                .andExpect(status().isOk());

        verify(accountService).editAccount(eq(1L), any(AccountDTO.class));
    }
}