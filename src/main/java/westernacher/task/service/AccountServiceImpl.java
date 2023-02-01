package westernacher.task.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import westernacher.task.dto.AccountDTO;
import westernacher.task.mapper.AccountMapper;
import westernacher.task.model.Account;
import westernacher.task.repository.AccountRepository;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;

        this.accountMapper = accountMapper;
    }

    @Override
    public List<Account> getAccountList() {
        return accountRepository.findAll();
    }

    @Override
    public void deleteAccount(Long accountId) {
        accountRepository.deleteAccountById(accountId);
    }

    @Override
    public void editAccount(Long accountId, AccountDTO accountDTO) {
        Optional<Account> accountOptional = accountRepository.getAccountById(accountId);
        if (accountOptional.isPresent()) {
            Account accountToBeEdited = accountOptional.get();
            accountMapper.updateAccountFromDto(accountDTO, accountToBeEdited);
            accountRepository.save(accountToBeEdited);
        }
    }
}
