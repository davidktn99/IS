import businessLayer.User;
import businessLayer.service.user.AuthenticationService;
import businessLayer.service.user.AuthenticationServiceMySQL;
import databaseLayer.DBConnectionFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import persistenceLayer.security.RightsRolesRepository;
import persistenceLayer.security.RightsRolesRepositoryMySQL;
import persistenceLayer.user.UserRepository;
import persistenceLayer.user.UserRepositoryMySQL;

import java.sql.Connection;

public class AuthenticationServiceMySQLTest {

    public static final String TEST_USERNAME = "admin";
    public static final String TEST_PASSWORD = "1337admin!";
    private static AuthenticationService authenticationService;

    @BeforeClass
    public static void setUp() {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(true).getConnection();
        RightsRolesRepository rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        UserRepository userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);
        authenticationService = new AuthenticationServiceMySQL(userRepository, rightsRolesRepository);
    }

    @Test
    public void login() throws Exception {
        User user = authenticationService.login(TEST_USERNAME, TEST_PASSWORD, "administrator").getResult();
        Assert.assertNotNull(user);
    }

}
