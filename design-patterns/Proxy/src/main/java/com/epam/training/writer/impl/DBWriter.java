package com.epam.training.writer.impl;

import com.epam.training.person.Person;
import com.epam.training.common.DBHelper;
import com.epam.training.writer.model.Writer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public final class DBWriter extends DBHelper implements Writer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBWriter.class);

    public DBWriter() throws SQLException {
        super();
    }

    @Override
    public void writePerson(Person person) throws SQLException {
        String sql = "INSERT INTO Person(name, age) VALUES (?, ?);";

        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.executeUpdate();

            LOGGER.debug("Person successfully added: " + person);

            connection.commit();
        }
        catch (SQLException e) {
            LOGGER.error("Unable to insert new Person into the database.", e);

            throw e;
        }
    }

}
