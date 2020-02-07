package net.sm.core.database;

import java.sql.*;

public class DBMS {

    private final String username;
    private final String password;

    public DBMS(String username, String password) {

        this.username = username;
        this.password = password;
    }

    public static void close(Connection connection) throws SQLException {
        if (connection != null)
            connection.close();
    }

    public static void close(Statement statement) throws SQLException {
        if (statement != null)
            statement.close();
    }

    public static void close(PreparedStatement preparedStatement) throws SQLException {
        if (preparedStatement != null)
            preparedStatement.close();
    }

    public static void close(ResultSet resultSet) throws SQLException {
        if (resultSet != null)
            resultSet.close();
    }


    public void runQuery(Connection connection, String query) throws SQLException {

        if (connection != null) {

            Statement statement = connection.createStatement();
            if (statement != null) {
                statement.execute(query);
                this.close(statement);
            }

            this.close(connection);
        }
    }

    public QueryResult executeQuery(Connection connection, String query) throws SQLException {

        if (connection != null) {

            Statement statement = connection.createStatement();
            if (statement != null) {

                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet != null)
                    return new QueryResult(resultSet, statement, connection);

                this.close(statement);
            }
            this.close(connection);
        }

        return null;
    }


    public Integer runQueryReturnID(Connection connection, String query) throws SQLException {

        Integer id = -1;

        if (connection != null) {

            PreparedStatement preparedStatement = connection.prepareStatement(query, new String[]{"ID"});
            if (preparedStatement != null) {

                preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();

                if (resultSet != null) {

                    if (resultSet.next())
                        id = resultSet.getInt(1);

                    this.close(resultSet);
                }
                this.close(preparedStatement);
            }
            this.close(connection);
        }
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
