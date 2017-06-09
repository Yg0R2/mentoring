package com.epam.training.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DBHelper {
    private static final String HSQL_DRIVER = "org.hsqldb.jdbc.JDBCDriver";
    private static final String HSQL_USER = "SA";
    private static final String HSQL_PASSWORD = "";
    private static final String HSQL_URL = "jdbc:hsqldb:target/db/hsq";
    private static final Logger LOGGER = LoggerFactory.getLogger(DBHelper.class);

    public DBHelper() throws SQLException {
        createPersonDB();
    }

    protected final Connection getConnection() throws SQLException {
        try {
            Class.forName(HSQL_DRIVER);
        }
        catch (Exception e) {
            LOGGER.error("JDBC driver is missing: " + HSQL_DRIVER, e);

            throw new RuntimeException(e);
        }

        try {
            return DriverManager.getConnection(HSQL_URL, HSQL_USER, HSQL_PASSWORD);
        }
        catch (SQLException e) {
            LOGGER.error("Unable to establish connection with the database.", e);

            throw e;
        }
    }

    private final void createPersonDB() throws SQLException {
        LOGGER.debug("Checking 'Person' table exists.");

        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS Person (");
        sql.append("    ID INT IDENTITY PRIMARY KEY,");
        sql.append("    NAME VARCHAR(50) NOT NULL,");
        sql.append("    AGE INT NOT NULL");
        sql.append(");");

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql.toString());
        }
        catch (SQLException e) {
            LOGGER.error("Unable to create table: 'Person'.", e);

            throw e;
        }
    }

}
