package com.thbs.Spring.Project.exception;

public class EmployeeNotExistsException extends RuntimeException{
    private static final long serialVersionId=0;

    public EmployeeNotExistsException() {
    }

    public EmployeeNotExistsException(String message) {
        super(message);
    }
}
