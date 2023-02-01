package westernacher.task.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import westernacher.task.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    List<Account> findAll();

    void deleteAccountById(Long id);

    Optional<Account> getAccountById(Long id);

}
