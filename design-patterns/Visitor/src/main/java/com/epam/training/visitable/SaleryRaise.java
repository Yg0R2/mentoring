package com.epam.training.visitable;

import com.epam.training.employee.Employee;
import com.epam.training.visitor.Visitor;

import java.util.List;

public class SaleryRaise extends AbstractVisitable implements Visitable {

    private int raiseOfPercentage;

    public SaleryRaise(List<Employee> employees, int raiseOfPercentage) {
        super(employees);
        this.raiseOfPercentage = raiseOfPercentage;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public int getRaiseOfPercentage() {
        return raiseOfPercentage;
    }

}
