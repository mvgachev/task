package westernacher.task.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import westernacher.task.dto.AccountDTO;
import westernacher.task.mapper.AccountMapper;
import westernacher.task.model.Sorting;
import westernacher.task.service.AccountService;

@RestController
@RequestMapping("accounts")
public class AccountController {

    private final AccountService accountService;
    private final AccountMapper accountMapper;

    public AccountController(AccountService accountService, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }

    @GetMapping("/all")
    public @ResponseBody List<AccountDTO> getAllAccounts(@RequestBody Sorting sorting) {

        return accountService.getAccountList().stream()
                .map(accountMapper::convertToAccountDTO)
                .sorted(sorting.getSortingComparator())
                .collect(Collectors.toList());

    }

    @DeleteMapping("/delete/{accountId}")
    public void deleteAccount(@PathVariable("accountId") Long accountId) {
        accountService.deleteAccount(accountId);
    }

    @PutMapping("/edit/{accountId}")
    public void editAccount(@PathVariable("accountId") Long accountId, @RequestBody AccountDTO accountDTO) {
        accountService.editAccount(accountId, accountDTO);
    }
}
