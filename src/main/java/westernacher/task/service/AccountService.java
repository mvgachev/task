package westernacher.task.service;

import java.util.List;

import westernacher.task.dto.AccountDTO;
import westernacher.task.model.Account;

public interface AccountService {

    List<Account> getAccountList();

    void deleteAccount(Long accountId);

    void editAccount(Long accountId, AccountDTO accountDTO);
}
