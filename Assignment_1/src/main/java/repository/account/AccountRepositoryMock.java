package repository.account;

import model.Account;
import repository.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountRepositoryMock implements AccountRepository {
    private List<Account> accounts;

    public AccountRepositoryMock() {
        accounts = new ArrayList<>();
    }

    @Override
    public List<Account> getAccounts() {
        return accounts;
    }

    @Override
    public boolean addAccount(Account acc) {
        return accounts.add(acc);
    }

    @Override
    public void deleteById(Long id) throws EntityNotFoundException {
        //TODO: Implement deleteIdMock
    }

    @Override
    public void updateById(Long id) throws EntityNotFoundException {
        //TODO: Implement updateIdMock
    }

    @Override
    public void deleteAll() {
        accounts.clear();
    }

    @Override
    public Account findById(Long id) throws EntityNotFoundException {
        List<Account> filteredAccounts = accounts.parallelStream()
                .filter(it -> it.getId().equals(id))
                .collect(Collectors.toList());
        if (filteredAccounts.size() > 0) {
            return filteredAccounts.get(0);
        }
        throw new EntityNotFoundException(id, Account.class.getSimpleName());
    }
}
