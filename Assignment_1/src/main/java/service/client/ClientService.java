package service.client;

import model.Client;
import repository.EntityNotFoundException;

import java.util.List;

/**
 * Created by David Katona on 18/11/2020
 */
public interface ClientService {
    List<Client> getClients();

    Client findById(Long id) throws EntityNotFoundException;

    boolean addClient(Client client);

    void updateById(Long id);
}
