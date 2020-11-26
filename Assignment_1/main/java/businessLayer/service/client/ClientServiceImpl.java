package businessLayer.service.client;

import businessLayer.Client;
import persistenceLayer.EntityNotFoundException;
import persistenceLayer.client.ClientRepository;

import java.util.List;

/**
 * Created by David Katona on 18/11/2020
 */
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public List<Client> getClients() {
        return clientRepository.getClients();
    }

    @Override
    public Client findById(Long id) throws EntityNotFoundException {
        return clientRepository.findById(id);
    }

    @Override
    public boolean addClient(Client client) {
        return clientRepository.addClient(client);
    }

    @Override
    public boolean updateById(Long id, String name, Long pnc, String address) {
        return clientRepository.updateById(id, name, pnc, address);
    }

    @Override
    public void removeAll() {
        clientRepository.removeAll();
    }
}
