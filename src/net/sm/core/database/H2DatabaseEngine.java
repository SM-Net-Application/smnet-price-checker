package net.sm.core.database;

import org.h2.jdbcx.JdbcConnectionPool;
import org.h2.jdbcx.JdbcDataSource;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class H2DatabaseEngine extends DBMS {

    private final String driver;
    private final File database;

    private Boolean autoServer;
    private Boolean ifExists;

    private JdbcConnectionPool connectionPool;

    public H2DatabaseEngine(File database, String username, String password, Boolean autoServer, Boolean ifExists) {
        super(username, password);

        this.driver = "jdbc:h2:file:";
        this.database = database;

        this.autoServer = autoServer;
        this.ifExists = ifExists;

        this.connect();
    }

    public String connectionString() {

        String connectionString = "";

        if (this.database != null) {

            String directory = this.database.getParentFile().getAbsolutePath();
            String databaseName = this.database.getName().replaceAll("\\.mv\\.db$", "");

            connectionString = String.format("%s%s%s%s", this.driver, directory, File.separator, databaseName);

            if (this.autoServer)
                connectionString += ";AUTO_SERVER=TRUE";

            if (this.ifExists)
                connectionString += ";IFEXISTS=TRUE";
        }

        return connectionString;
    }

    private JdbcDataSource dataSource(String url) {

        JdbcDataSource jdbcDataSource = new JdbcDataSource();

        jdbcDataSource.setUrl(url);
        jdbcDataSource.setUser(this.getUsername());
        jdbcDataSource.setPassword(this.getPassword());

        return jdbcDataSource;
    }

    private void connect() {

        JdbcDataSource dataSource = this.dataSource(this.connectionString());
        if (dataSource != null)
            this.connectionPool = JdbcConnectionPool.create(dataSource);
    }

    public void disconnect() {

        if (this.connectionPool != null)
            this.connectionPool.dispose();
    }

    public Connection getConnection() throws SQLException {

        if (this.connectionPool != null)
            return this.connectionPool.getConnection();

        return null;
    }

    public Boolean getAutoServer() {
        return autoServer;
    }

    public void setAutoServer(Boolean autoServer) {
        this.autoServer = autoServer;
    }

    public Boolean getIfExists() {
        return ifExists;
    }

    public void setIfExists(Boolean ifExists) {
        this.ifExists = ifExists;
    }
}
