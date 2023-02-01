package westernacher.task;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

import java.util.Date;
import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;
import westernacher.task.model.Account;
import westernacher.task.repository.AccountRepository;

@Component
@Log4j2
public class BootStrap {

    private final AccountRepository accountRepository;

    public BootStrap(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    private void init() {
        try {
            log.info("BootStrap start");
            Account account =
                    Account.builder().firstName("Account1").lastName("2").emailAddress("d").dateOfBirth(new Date())
                            .build();
            Account account1 =
                    Account.builder().firstName("test2").lastName("test22").emailAddress("a").dateOfBirth(new Date())
                            .build();
            Account account2 =
                    Account.builder().firstName("pasta").lastName("pasta2").dateOfBirth(new Date()).emailAddress("b")
                            .build();
            Account account3 =
                    Account.builder().firstName("air").lastName("air2").dateOfBirth(new Date()).emailAddress("c")
                            .build();
            accountRepository.saveAll(List.of(account, account1, account2, account3));
            log.info("BootStrap success, the saved account has name - " + account.getFirstName());
        } catch (Exception e) {
            log.error("BootStrap failed," + e);
            e.printStackTrace();
            throw e;
        }
    }

}
