package com.epam.training.person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper {

    public Person mapRow(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        int age = rs.getInt("age");

        return new Person(name, age);
    }

}
