package westernacher.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import westernacher.task.model.Account;
import westernacher.task.repository.AccountRepository;

@SpringBootApplication
public class TaskApplication {

    @Autowired
    private static AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }

}
