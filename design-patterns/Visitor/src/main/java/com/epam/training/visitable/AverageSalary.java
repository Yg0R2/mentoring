package com.epam.training.visitable;


import com.epam.training.employee.Employee;
import com.epam.training.visitor.Visitor;

import java.util.List;

public final class AverageSalary extends AbstractVisitable implements Visitable {

    public AverageSalary(List<Employee> employees) {
        super(employees);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
