import businessLayer.service.account.AccountService;
import businessLayer.service.account.AccountServiceImpl;
import businessLayer.service.client.ClientService;
import businessLayer.service.client.ClientServiceImpl;
import businessLayer.service.user.AuthenticationService;
import businessLayer.service.user.AuthenticationServiceMySQL;
import databaseLayer.DBConnectionFactory;
import persistenceLayer.account.AccountRepositoryMySQL;
import persistenceLayer.client.ClientRepositoryMySQL;
import persistenceLayer.security.RightsRolesRepository;
import persistenceLayer.security.RightsRolesRepositoryMySQL;
import persistenceLayer.user.UserRepository;
import persistenceLayer.user.UserRepositoryMySQL;

import java.sql.Connection;

/**
 * Created by David Katona on 18/11/2020
 */
public class ComponentFactory {

    private static ComponentFactory instance;
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    private final AccountService accountService;
    private final ClientService clientService;

    private ComponentFactory(Boolean componentsForTests) {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(componentsForTests).getConnection();
        RightsRolesRepository rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        this.accountService = new AccountServiceImpl(new AccountRepositoryMySQL(connection));
        this.clientService = new ClientServiceImpl(new ClientRepositoryMySQL(connection));
        this.userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);
        this.authenticationService = new AuthenticationServiceMySQL(this.userRepository, rightsRolesRepository);
    }

    public static ComponentFactory instance(Boolean componentsForTests) {
        if (instance == null) {
            instance = new ComponentFactory(componentsForTests);
        }
        return instance;
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public ClientService getClientService() {
        return clientService;
    }
}

