package repository;

import model.Book;
import model.builder.BookBuilder;
import utils.JDBCConnectionWrapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookRepositoryMySql implements BookRepository {

    private final JDBCConnectionWrapper connectionWrapper;

    public BookRepositoryMySql(JDBCConnectionWrapper connectionWrapper) {
        this.connectionWrapper = connectionWrapper;
    }

    public JDBCConnectionWrapper getConnectionWrapper() {
        return connectionWrapper;
    }

    @Override
    public List<Book> findAll() {

        Connection connection = connectionWrapper.getConnection();
        List<Book> books = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM book;";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                books.add((getBookFromResultSet(resultSet)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Book findById(Long id) {
        return null;
    }

    @Override
    public boolean save(Book book) {

        Connection connection = connectionWrapper.getConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO book VALUES (null, " + book.getAuthor() + ", " + book.getTitle() + ", " + book.getPublishedDate() + ")\");";

            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO book VALUES (null, ?, ?, ?");
            insertStatement.setString(1, book.getAuthor());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void removeAll() {

    }

    private Book getBookFromResultSet(ResultSet resultSet) throws SQLException {
        return new BookBuilder()
                .setId(resultSet.getLong("id"))
                .setTitle(resultSet.getString("title"))
                .setAuthor((resultSet.getString("author")))
                .setPublishedDate((new Date(resultSet.getDate("publishedDate").getTime())))
                .build();
    }
}
