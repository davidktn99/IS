package repository.client;

import model.Client;
import repository.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientRepositoryMock implements ClientRepository{
    private List<Client> clients;

    public ClientRepositoryMock(){
        clients = new ArrayList<>();
    }

    @Override
    public List<Client> getClients() {
        return clients;
    }

    @Override
    public Client findById(Long id)  throws EntityNotFoundException{
        List<Client> filteredClients = clients.parallelStream()
                .filter(it -> it.getId().equals(id))
                .collect(Collectors.toList());
        if (filteredClients.size() > 0) {
            return filteredClients.get(0);
        }
        throw new EntityNotFoundException(id, Client.class.getSimpleName());
    }

    @Override
    public boolean addClient(Client client) {
        return clients.add(client);
    }

    @Override
    public void updateById(Long id)  throws EntityNotFoundException{
        //TODO: Implement updateIdMock
    }

}
