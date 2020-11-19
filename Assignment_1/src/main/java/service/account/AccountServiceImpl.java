package service.account;

import model.Account;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;

import java.util.List;

/**
 * Created by David Katona on 18/11/2020
 */
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Account> getAccounts() {
        return repository.getAccounts();
    }

    @Override
    public boolean addAccount(Account acc) {
        return repository.addAccount(acc);
    }

    @Override
    public void deleteById(Long id) {
        //TODO
    }

    @Override
    public void updateById(Long id) {
        //TODO
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Account findById(Long id) throws EntityNotFoundException {
        return repository.findById(id);
    }

    @Override
    public void transferMoney(Long src, Long dest, float amount) throws EntityNotFoundException {
        Account srcAcc = repository.findById(src);
        Account destAcc = repository.findById(dest);

        if (srcAcc.getMoney() < amount) {
            System.out.println("No dinero");
            return;
        }

        srcAcc.setMoney(srcAcc.getMoney() - amount);
        destAcc.setMoney(destAcc.getMoney() + amount);
    }
}
