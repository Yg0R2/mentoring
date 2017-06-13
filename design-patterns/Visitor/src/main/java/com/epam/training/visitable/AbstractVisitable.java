package com.epam.training.visitable;

import com.epam.training.employee.Employee;

import java.util.List;

/**
 * @author Yg0R2
 */
public abstract class AbstractVisitable implements Visitable {

    private List<Employee> employees;

    public AbstractVisitable(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

}
