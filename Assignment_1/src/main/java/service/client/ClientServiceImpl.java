package service.client;

import model.Client;
import repository.EntityNotFoundException;
import repository.client.ClientRepository;

import java.util.List;

/**
 * Created by David Katona on 18/11/2020
 */
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Client> getClients() {
        return repository.getClients();
    }

    @Override
    public Client findById(Long id) throws EntityNotFoundException {
        return repository.findById(id);
    }

    @Override
    public boolean addClient(Client client) {
        return repository.addClient(client);
    }

    @Override
    public void updateById(Long id) {
        //TODO
    }
}
