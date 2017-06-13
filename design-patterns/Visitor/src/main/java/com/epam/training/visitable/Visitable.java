package com.epam.training.visitable;

import com.epam.training.visitor.Visitor;

public interface Visitable {

    void accept(Visitor visitor);

}
