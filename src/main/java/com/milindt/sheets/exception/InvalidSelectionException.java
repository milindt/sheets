package com.milindt.sheets.exception;

public class InvalidSelectionException extends RuntimeException{

    public InvalidSelectionException(String s) {
        super("Invalid selection format: " + s);
    }

}
