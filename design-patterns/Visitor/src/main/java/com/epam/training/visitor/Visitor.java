package com.epam.training.visitor;

import com.epam.training.employee.Employee;
import com.epam.training.visitable.*;

import java.util.List;
import java.util.Map;

public interface Visitor {

    long visit(AverageSalary averageSalary);

    Map<String, Integer> visit(EmployeesPerDepartmentCount employeesPerDepartment);

    int visit(NumberOfEmployees numberOfEmployees);

    List<Employee> visit(SaleryRaise saleryRaise);

    long visit(TotalSalary totalSalariOperation);

}
