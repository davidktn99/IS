package persistenceLayer.client;

import businessLayer.Client;
import businessLayer.builder.ClientBuilder;
import persistenceLayer.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepositoryMySQL implements ClientRepository {
    private final Connection connection;

    public ClientRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Client> getClients() {
        List<Client> clients = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM client";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                clients.add(getClientFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    @Override
    public Client findById(Long id) throws EntityNotFoundException {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM client WHERE id=" + id;
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return getClientFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(id, Client.class.getSimpleName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(id, Client.class.getSimpleName());
        }
    }

    @Override
    public boolean addClient(Client client) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO client VALUES (null, ?, ?, ?)");
            insertStatement.setLong(1, client.getPnc());
            insertStatement.setString(2, client.getName());
            insertStatement.setString(3, client.getAddress());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateById(Long id, String name, Long pnc, String address) {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE client SET name = '" + name
                    + "', pnc = " + pnc + ", address = '" + address + "' WHERE id = " + id;
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM client WHERE id > 0";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Client getClientFromResultSet(ResultSet rs) throws SQLException {
        return new ClientBuilder()
                .setId(rs.getLong("id"))
                .setPnc(rs.getLong("pnc"))
                .setName(rs.getString("name"))
                .setAddress((rs.getString("address")))
                .build();
    }

}
