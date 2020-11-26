package persistenceLayer.account;

import businessLayer.Account;
import businessLayer.builder.AccountBuilder;
import persistenceLayer.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryMySQL implements AccountRepository {

    private final Connection connection;

    public AccountRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Account> getAccounts() {
        List<Account> accounts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM account";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                accounts.add(getAccountFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public boolean addAccount(Account acc) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO account values (null, ?, ?, ?, ?)");
            insertStatement.setString(1, acc.getType());
            insertStatement.setFloat(2, acc.getMoney());
            insertStatement.setTimestamp(3, new Timestamp(acc.getDate().getTime()));
            insertStatement.setFloat(4, acc.getClientId());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM account WHERE id = " + id;
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateById(Long id, String type, float money, Long clientId) {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE account SET type = '" + type
                    + "', money = " + money + ", client_id = " + clientId + " WHERE id = " + id;
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Long getClientId(Long accId) throws EntityNotFoundException {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT client_id FROM account WHERE id=" + accId;
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return getClientIdFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(accId, Account.class.getSimpleName());
            }
        } catch (SQLException | EntityNotFoundException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(accId, Account.class.getSimpleName());
        }
    }


    @Override
    public Account findById(Long id) throws EntityNotFoundException {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM account WHERE id=" + id;
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return getAccountFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(id, Account.class.getSimpleName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(id, Account.class.getSimpleName());
        }
    }

    @Override
    public boolean transfer(Account src, Account dest, float money) {
        if (src.getMoney() < money)
            return false;
        float newSrc = src.getMoney() - money;
        float newDest = dest.getMoney() + money;
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE account SET money =" + newSrc + " WHERE id = " + src.getId();
            statement.executeUpdate(query);
            query = "UPDATE account SET money =" + newDest + " WHERE id = " + dest.getId();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void deleteAll() {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM account WHERE id > 0";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Account getAccountFromResultSet(ResultSet rs) throws SQLException {
        return new AccountBuilder()
                .setId(rs.getLong("id"))
                .setType(rs.getString("type"))
                .setMoney(rs.getFloat("money"))
                .setDate(new Date(rs.getDate("date").getTime()))
                .setClientId(rs.getLong("client_id"))
                .build();
    }

    private Long getClientIdFromResultSet(ResultSet rs) throws SQLException {
        return rs.getLong("client_id");
    }
}
