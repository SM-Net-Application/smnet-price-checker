package net.sm.core.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryResult {

    private ResultSet resultSet;
    private Statement statement;
    private Connection connection;

    public QueryResult(ResultSet resultSet, Statement statement, Connection connection) {

        this.resultSet = resultSet;
        this.statement = statement;
        this.connection = connection;
    }

    public void close() throws SQLException {

        if (this.resultSet != null)
            this.resultSet.close();
        if (this.statement != null)
            this.statement.close();
        if (this.connection != null)
            this.connection.close();
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }
}
