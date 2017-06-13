package com.epam.training;

import com.epam.training.company.Company;
import com.epam.training.employee.Employee;
import com.epam.training.visitable.*;
import com.epam.training.visitor.Visitor;
import com.epam.training.visitor.VisitorImpl;

import java.util.*;

public class Application {

    private static final Random RND = new Random();

    public static void main(String[] args) {
        Application instance = new Application();

        Company company = instance.generateCompany(10);

        Visitor visitor = new VisitorImpl();
        for (Visitable visitable : instance.getVisitables(company.getEmployees())) {
            visitable.accept(visitor);
        }

    }

    private Company generateCompany(int employeesCount) {
        Company company = new Company();

        for (int i = 0; i < employeesCount; i++) {
            Employee employee = new Employee();
            employee.setDepartment("department" + RND.nextInt(2));
            employee.setName("Name" + i);
            employee.setSalary(RND.nextInt(10000) + 1000);

            company.addEmployee(employee);
        }

        return company;
    }

    private List<Visitable> getVisitables(List<Employee> employees) {
        List<Visitable> visitables  = new ArrayList<>();

        visitables.add(new TotalSalary(employees));
        visitables.add(new NumberOfEmployees(employees));
        visitables.add(new AverageSalary(employees));
        visitables.add(new EmployeesPerDepartmentCount(employees));
        visitables.add(new SaleryRaise(employees, 30));

        return visitables;
    }

}
