package repository.client;

import model.Client;
import repository.Cache;
import repository.EntityNotFoundException;

import java.util.List;

public class ClientRepositoryCacheDecorator extends ClientRepositoryDecorator {

    private Cache<Client> cache;

    public ClientRepositoryCacheDecorator(ClientRepository repo, Cache<Client> cache) {
        super(repo);
        this.cache = cache;
    }

    @Override
    public List<Client> getClients() {
        if (cache.hasResult()) {
            return cache.load();
        }
        List<Client> clients = decoratedRepository.getClients();
        cache.save(clients);
        return clients;
    }

    @Override
    public Client findById(Long id) throws EntityNotFoundException {
        return decoratedRepository.findById(id);
    }

    @Override
    public boolean addClient(Client client) {
        cache.invalidateCache();
        return decoratedRepository.addClient(client);
    }

    @Override
    public void updateById(Long id) throws EntityNotFoundException {
        //TODO: IMPLEMENT updateIdCache
    }

}
