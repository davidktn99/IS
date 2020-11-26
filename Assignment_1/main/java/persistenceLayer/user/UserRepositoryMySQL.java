package persistenceLayer.user;

import businessLayer.User;
import businessLayer.builder.UserBuilder;
import businessLayer.validation.Notification;
import persistenceLayer.security.RightsRolesRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static databaseLayer.Constants.Tables.USER;

/**
 * Created by Alex on 11/03/2017.
 */
public class UserRepositoryMySQL implements UserRepository {

    private final Connection connection;
    private final RightsRolesRepository rightsRolesRepository;


    public UserRepositoryMySQL(Connection connection, RightsRolesRepository rightsRolesRepository) {
        this.connection = connection;
        this.rightsRolesRepository = rightsRolesRepository;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM user";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                users.add(getUserFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public Notification<User> findByUsernameAndPassword(String username, String password, String mode) throws AuthenticationException {
        Notification<User> findByUsernameAndPasswordNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String fetchUserSql = "SELECT * FROM `" + USER + "` WHERE `username`='" + username + "' AND `password`='" + password + "'";
            ResultSet userResultSet = statement.executeQuery(fetchUserSql);
            if (userResultSet.next()) {
                User user = new UserBuilder()
                        .setUsername(userResultSet.getString("username"))
                        .setPassword(userResultSet.getString("password"))
                        .setRoles(rightsRolesRepository.findRolesForUser(userResultSet.getLong("id")))
                        .build();
                if (user.getRoles().get(0).getRole().equals("administrator"))
                    findByUsernameAndPasswordNotification.setResult(user);
                else if (user.getRoles().get(0).getRole().equals(mode))
                    findByUsernameAndPasswordNotification.setResult(user);
                else findByUsernameAndPasswordNotification.addError("User does not have enough privileges!");
            } else {
                findByUsernameAndPasswordNotification.addError("Invalid username or password!");
            }
            return findByUsernameAndPasswordNotification;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AuthenticationException();
        }
    }

    @Override
    public boolean addUser(User user) {
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("INSERT INTO user values (null, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            insertUserStatement.setString(1, user.getUsername());
            insertUserStatement.setString(2, user.getPassword());
            insertUserStatement.executeUpdate();

            ResultSet rs = insertUserStatement.getGeneratedKeys();
            rs.next();
            long userId = rs.getLong(1);
            user.setId(userId);

            rightsRolesRepository.addRolesToUser(user, user.getRoles());

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean deleteById(String id) {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM " + USER + " WHERE id = " + id;
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean editById(String id, String username, String password) {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE user SET username = '" + username
                    + "', password = '" + password + "' WHERE id = " + id;
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        return new UserBuilder()
                .setId(rs.getLong("id"))
                .setUsername(rs.getString("username"))
                .setPassword(rs.getString("password"))
                .setRoles(new ArrayList<>())
                .build();
    }


}
