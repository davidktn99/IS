package service.account;

import model.Account;
import repository.EntityNotFoundException;

import java.util.List;

/**
 * Created by David Katona on 18/11/2020
 */
public interface AccountService {

    List<Account> getAccounts();

    boolean addAccount(Account acc);

    void deleteById(Long id);

    void updateById(Long id);

    void deleteAll();

    Account findById(Long id) throws EntityNotFoundException;

    void transferMoney(Long src, Long dest, float amount) throws EntityNotFoundException;
}
