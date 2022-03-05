package com.company;

public interface Visitor<T extends Entity> {
    void visit(T entity);
}
