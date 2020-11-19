package repository.account;

import model.Account;
import repository.Cache;
import repository.EntityNotFoundException;

import java.util.List;

public class AccountRepositoryCacheDecorator extends AccountRepositoryDecorator {
    private Cache<Account> cache;

    public AccountRepositoryCacheDecorator(AccountRepository repo, Cache<Account> cache) {
        super(repo);
        this.cache = cache;
    }

    @Override
    public List<Account> getAccounts() {
        if (cache.hasResult()) {
            return cache.load();
        }
        List<Account> accounts = decoratedRepository.getAccounts();
        cache.save(accounts);
        return accounts;
    }

    @Override
    public boolean addAccount(Account acc) {
        cache.invalidateCache();
        return decoratedRepository.addAccount(acc);
    }

    @Override
    public void deleteById(Long id) throws EntityNotFoundException {
        //TODO: Implement deleteIdCache
    }

    @Override
    public void updateById(Long id) throws EntityNotFoundException {
        //TODO: Implement updateIdCache
    }

    @Override
    public void deleteAll() {
        cache.invalidateCache();
        decoratedRepository.deleteAll();
    }

    @Override
    public Account findById(Long id) throws EntityNotFoundException {
        return decoratedRepository.findById(id);
    }
}
