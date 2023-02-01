package westernacher.task.mapper;

import org.springframework.stereotype.Component;

import westernacher.task.dto.AccountDTO;
import westernacher.task.model.Account;

@Component
public class AccountMapper {

    public AccountDTO convertToAccountDTO(Account account) {
        if (account == null) {
            return null;
        }

        return AccountDTO.builder()
                .id(account.getId())
                .emailAddress(account.getEmailAddress())
                .dateOfBirth(account.getDateOfBirth())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .build();
    }

    public Account convertToAccount(AccountDTO accountDTO) {
        if (accountDTO == null) {
            return null;
        }

        return Account.builder()
                .emailAddress(accountDTO.getEmailAddress())
                .dateOfBirth(accountDTO.getDateOfBirth())
                .firstName(accountDTO.getFirstName())
                .lastName(accountDTO.getLastName())
                .build();
    }

    public void updateAccountFromDto(AccountDTO accountDTO, Account account) {
        account.setDateOfBirth(
                accountDTO.getDateOfBirth() == null ? account.getDateOfBirth() : accountDTO.getDateOfBirth());
        account.setFirstName(accountDTO.getFirstName() == null ? account.getFirstName() : accountDTO.getFirstName());
        account.setEmailAddress(
                accountDTO.getEmailAddress() == null ? account.getEmailAddress() : accountDTO.getEmailAddress());
        account.setLastName(accountDTO.getLastName() == null ? account.getLastName() : accountDTO.getLastName());
    }
}
