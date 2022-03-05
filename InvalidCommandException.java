package com.company;

public class InvalidCommandException extends Exception {
    InvalidCommandException(String mesaj) {
        super(mesaj);
    }
}
