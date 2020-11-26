import businessLayer.builder.AccountBuilder;
import businessLayer.builder.ClientBuilder;
import databaseLayer.DBConnectionFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import persistenceLayer.account.AccountRepository;
import persistenceLayer.account.AccountRepositoryMySQL;
import persistenceLayer.client.ClientRepository;
import persistenceLayer.client.ClientRepositoryMySQL;

import java.sql.Connection;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class AccountRepositoryMySQLTest {

    private static AccountRepository accountRepository;
    private static ClientRepository clientRepository;

    @BeforeClass
    public static void setUp() {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(true).getConnection();
        accountRepository = new AccountRepositoryMySQL(connection);
        clientRepository = new ClientRepositoryMySQL(connection);
    }

    @Before
    public void cleanUp() {
        clientRepository.removeAll();
        clientRepository.addClient(new ClientBuilder()
                .setId(111L)
                .setName("Timmy")
                .setPnc(123456789L)
                .setAddress("21 Jump Street")
                .build()
        );
        accountRepository.deleteAll();
    }

    @Test
    public void createAccountTest() {
        assertTrue(accountRepository.addAccount(new AccountBuilder()
                .setId(1L)
                .setDate(new Date())
                .setMoney(14.0f)
                .setType("Savings")
                .setClientId(clientRepository.getClients().get(0).getId())
                .build()
        ));
    }
}

