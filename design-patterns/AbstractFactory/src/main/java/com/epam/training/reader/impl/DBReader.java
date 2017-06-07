package com.epam.training.reader.impl;

import com.epam.training.person.Person;
import com.epam.training.person.PersonRowMapper;
import com.epam.training.reader.model.Reader;
import com.epam.training.common.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class DBReader extends DBHelper implements Reader {

    private PersonRowMapper personRowMapper = new PersonRowMapper();

    public DBReader() throws SQLException {
        super();
    }

    @Override
    public Person readPerson(String name) throws SQLException {
        final String sql = "SELECT * FROM Person WHERE name = ?";

        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return personRowMapper.mapRow(resultSet);
            }
        }

        return null;
    }

    @Override
    public List<Person> readPersons() throws SQLException{
        final String sql = "SELECT * FROM Person;";

        List<Person> persons = new ArrayList<>();
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Person person = personRowMapper.mapRow(resultSet);

                persons.add(person);
            }
        }

        return persons;
    }

}
