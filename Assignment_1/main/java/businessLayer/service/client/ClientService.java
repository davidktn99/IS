package businessLayer.service.client;

import businessLayer.Client;
import persistenceLayer.EntityNotFoundException;

import java.util.List;

/**
 * Created by David Katona on 18/11/2020
 */
public interface ClientService {

    List<Client> getClients();

    Client findById(Long id) throws EntityNotFoundException;

    boolean addClient(Client client);

    boolean updateById(Long id, String name, Long pnc, String address);

    void removeAll();
}
