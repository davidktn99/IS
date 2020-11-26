import businessLayer.Client;
import businessLayer.builder.ClientBuilder;
import databaseLayer.DBConnectionFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import persistenceLayer.client.ClientRepository;
import persistenceLayer.client.ClientRepositoryMySQL;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClientRepositoryMySQLTest {

    private static ClientRepository clientRepository;

    @BeforeClass
    public static void setUp() {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(true).getConnection();
        clientRepository = new ClientRepositoryMySQL(connection);
    }

    @Before
    public void cleanUp() {
        clientRepository.removeAll();
    }

    @Test
    public void createClientTest() {
        assertTrue(clientRepository.addClient(new ClientBuilder()
                        .setId(111L)
                        .setName("Timmy")
                        .setPnc(123456789L)
                        .setAddress("21 Jump Street")
                        .build()
                )
        );
    }

    @Test
    public void testAdded() {
        Client client = new ClientBuilder()
                .setId(111L)
                .setName("Timmy")
                .setPnc(123456789L)
                .setAddress("21 Jump Street")
                .build();
        clientRepository.addClient(client);
        clientRepository.addClient(client);
        assertEquals(clientRepository.getClients().size(), 2);
    }

    @Test
    public void editClientTest() {
        clientRepository.addClient(new ClientBuilder()
                .setId(111L)
                .setName("Timmy")
                .setPnc(123456789L)
                .setAddress("21 Jump Street")
                .build());
        assertTrue(clientRepository.updateById(1L, "Timmy", 12345L, "22. Jump Street"));
        assertEquals(clientRepository.getClients().get(0).getAddress(), "22. Jump Street");

    }


}

