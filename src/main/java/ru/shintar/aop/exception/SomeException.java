package ru.shintar.aop.exception;

public class SomeException extends RuntimeException{
    public SomeException (String method) {
        super(method + " some exception");
    }
}
