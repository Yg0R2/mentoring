package com.epam.training.visitor;

import com.epam.training.visitable.*;
import com.epam.training.employee.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class VisitorImpl implements Visitor {

    private static final Logger LOGGER = LoggerFactory.getLogger(VisitorImpl.class);

    @Override
    public long visit(AverageSalary averageSalary) {
        List<Employee> employees = averageSalary.getEmployees();

        long totalSalary = employees.stream().map(e -> e.getSalary()).mapToLong(Long::longValue).sum();

        long avgSalary = totalSalary / employees.size();

        LOGGER.info("Average salary: " + avgSalary);

        return avgSalary;
    }

    @Override
    public Map<String, Integer> visit(EmployeesPerDepartmentCount employeesPerDepartmentCount) {
        Map<String, Integer> employeesPerDepartmentCountMap = new HashMap<>();

        for(Employee employee : employeesPerDepartmentCount.getEmployees()) {
            String department = employee.getDepartment();

            Integer employeesCountInDepartment = employeesPerDepartmentCountMap.get(department);
            if (employeesCountInDepartment == null) {
                employeesCountInDepartment = 0;
            }

            employeesCountInDepartment++;

            employeesPerDepartmentCountMap.put(department, employeesCountInDepartment);
        }

        LOGGER.info("Employees count per department: " + Arrays.asList(employeesPerDepartmentCountMap));

        return employeesPerDepartmentCountMap;
    }

    @Override
    public int visit(NumberOfEmployees numberOfEmployees) {
        List<Employee> employees = numberOfEmployees.getEmployees();

        int employeesCount = employees.size();

        LOGGER.info("Employees count: " + employeesCount);

        return employeesCount;
    }

    @Override
    public List<Employee> visit(SaleryRaise saleryRaise) {
        List<Employee> employees = new ArrayList<>(saleryRaise.getEmployees());

        for (Employee employee : employees) {
            long salary = employee.getSalary();
            long newSalary = (long)(salary * (1.0 + saleryRaise.getRaiseOfPercentage() / 100.0));

            employee.setSalary(newSalary);

            LOGGER.info("Name: " + employee.getName() + ", salary: " + salary + ", new salary: " + newSalary);
        }

        return employees;
    }

    @Override
    public long visit(TotalSalary totalSalariOperation) {
        List<Employee> employees = totalSalariOperation.getEmployees();

        long totalSalary = employees.stream().map(e -> e.getSalary()).mapToLong(Long::longValue).sum();

        LOGGER.info("Total salary: " + totalSalary);

        return totalSalary;
    }

}
