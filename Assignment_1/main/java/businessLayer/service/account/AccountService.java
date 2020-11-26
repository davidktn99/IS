package businessLayer.service.account;

import businessLayer.Account;
import persistenceLayer.EntityNotFoundException;

import java.util.List;

/**
 * Created by David Katona on 18/11/2020
 */
public interface AccountService {

    List<Account> getAccounts();

    boolean addAccount(Account acc);

    boolean deleteById(Long id);

    boolean updateById(Long id, String type, float money, Long clientId);

    Long getClientId(Long accId) throws EntityNotFoundException;

    Account findById(Long id) throws EntityNotFoundException;

    boolean transfer(Account src, Account dest, float money);

    void deleteAll();
}
