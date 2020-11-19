package repository.account;

import model.Account;
import repository.EntityNotFoundException;

import java.util.List;

/**
 * Created by David Katona on 18/11/2020
 */
public interface AccountRepository {

    List<Account> getAccounts();

    boolean addAccount(Account acc);

    void deleteById(Long id)  throws EntityNotFoundException;

    void updateById(Long id)  throws EntityNotFoundException;

    void deleteAll();

    Account findById(Long id) throws EntityNotFoundException;


}
