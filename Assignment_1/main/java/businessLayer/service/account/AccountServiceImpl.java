package businessLayer.service.account;

import businessLayer.Account;
import persistenceLayer.EntityNotFoundException;
import persistenceLayer.account.AccountRepository;

import java.util.List;

/**
 * Created by David Katona on 18/11/2020
 */
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public List<Account> getAccounts() {
        return accountRepository.getAccounts();
    }

    @Override
    public boolean addAccount(Account acc) {
        return accountRepository.addAccount(acc);
    }

    @Override
    public boolean deleteById(Long id) {
        return accountRepository.deleteById(id);
    }

    @Override
    public boolean updateById(Long id, String type, float money, Long clientId) {
        return accountRepository.updateById(id, type, money, clientId);
    }

    @Override
    public Long getClientId(Long accId) throws EntityNotFoundException {
        return accountRepository.getClientId(accId);
    }

    @Override
    public Account findById(Long id) throws EntityNotFoundException {
        return accountRepository.findById(id);
    }

    @Override
    public boolean transfer(Account src, Account dest, float money) {
        return accountRepository.transfer(src, dest, money);
    }

    @Override
    public void deleteAll() {
        accountRepository.deleteAll();
    }
}
