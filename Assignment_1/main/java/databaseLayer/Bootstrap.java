package databaseLayer;

import businessLayer.Role;
import businessLayer.User;
import businessLayer.builder.UserBuilder;
import persistenceLayer.security.RightsRolesRepository;
import persistenceLayer.security.RightsRolesRepositoryMySQL;
import persistenceLayer.user.UserRepository;
import persistenceLayer.user.UserRepositoryMySQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static databaseLayer.Constants.Rights.RIGHTS;
import static databaseLayer.Constants.Roles.*;
import static databaseLayer.Constants.Schemas.SCHEMAS;
import static databaseLayer.Constants.getRolesRights;

/**
 * Created by David Katona on 18/11/2020
 */
public class Bootstrap {

    private static RightsRolesRepository rightsRolesRepository;
    private static UserRepository userRepository;

    public static void main(String[] args) throws SQLException {
        dropAll();

        bootstrapTables();

        bootstrapUserData();
    }

    private static void dropAll() throws SQLException {
        for (String schema : SCHEMAS) {
            System.out.println("Dropping all tables in schema: " + schema);

            Connection connection = new JDBConnectionWrapper(schema).getConnection();
            Statement statement = connection.createStatement();

            String[] dropStatements = {
                    "TRUNCATE `role_right`;",
                    "DROP TABLE `role_right`;",
                    "TRUNCATE `right`;",
                    "TRUNCATE `user_role`;",
                    "DROP TABLE `user_role`;",
                    "TRUNCATE `role`;",
                    "DROP TABLE `account`, `client`, `role`, `user`;"
            };

            Arrays.stream(dropStatements).forEach(dropStatement -> {
                try {
                    statement.execute(dropStatement);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }

        System.out.println("Done table bootstrap");
    }

    private static void bootstrapTables() throws SQLException {
        SQLTableCreationFactory sqlTableCreationFactory = new SQLTableCreationFactory();

        for (String schema : SCHEMAS) {
            System.out.println("Bootstrapping " + schema + " schema");

            JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper(schema);
            Connection connection = connectionWrapper.getConnection();

            Statement statement = connection.createStatement();

            for (String table : Constants.Tables.ORDERED_TABLES_FOR_CREATION) {
                String createTableSQL = sqlTableCreationFactory.getCreateSQLForTable(table);
                statement.execute(createTableSQL);
            }
        }
        System.out.println("Done table bootstrap");
    }

    private static void bootstrapUserData() {
        for (String schema : SCHEMAS) {
            System.out.println("Bootstrapping user data for " + schema);

            JDBConnectionWrapper connectionWrapper = new JDBConnectionWrapper(schema);
            rightsRolesRepository = new RightsRolesRepositoryMySQL(connectionWrapper.getConnection());
            userRepository = new UserRepositoryMySQL(connectionWrapper.getConnection(), rightsRolesRepository);

            bootstrapRoles();
            bootstrapRights();
            bootstrapRoleRight();
            bootstrapUsers();
        }
    }

    private static void bootstrapUsers() {
        List<Role> adminRoles = new ArrayList<>();
        adminRoles.add(rightsRolesRepository.findRoleByTitle(ADMINISTRATOR));
        User admin = new UserBuilder()
                .setUsername("admin")
                .setPassword("c0a4e4d84fc39e8a3f37462ab4f4389b34d1c8c225b2d7c2c1a33d0330fe8ba4")
                .setRoles(adminRoles)
                .build();

        List<Role> employeeRoles = new ArrayList<>();
        employeeRoles.add(rightsRolesRepository.findRoleByTitle(EMPLOYEE));
        User employee = new UserBuilder()
                .setUsername("emp")
                .setPassword("aff780264cba702eb4eae8c01ffee5e46a86f975074ad0fb9aa699c08b7cfa99")
                .setRoles(employeeRoles)
                .build();
        userRepository.addUser(admin);
        userRepository.addUser(employee);
    }

    private static void bootstrapRoles() {
        for (String role : ROLES) {
            rightsRolesRepository.addRole(role);
        }
    }

    private static void bootstrapRights() {
        for (String right : RIGHTS) {
            rightsRolesRepository.addRight(right);
        }
    }

    private static void bootstrapRoleRight() {
        Map<String, List<String>> rolesRights = getRolesRights();

        for (String role : rolesRights.keySet()) {
            Long roleId = rightsRolesRepository.findRoleByTitle(role).getId();
            for (String right : rolesRights.get(role)) {
                Long rightId = rightsRolesRepository.findRightByTitle(right).getId();
                rightsRolesRepository.addRoleRight(roleId, rightId);
            }
        }
    }
}
