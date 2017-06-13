package com.epam.training.company;

import com.epam.training.employee.Employee;

import java.util.ArrayList;
import java.util.List;

public class Company {

    private List<Employee> employees = new ArrayList<Employee>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

}
