package com.company;

public interface Element<T extends Entity> {
    void accept(Visitor<T> visitor);
}
