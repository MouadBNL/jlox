package org.example.Lox.Exception;

public class BreakStatement extends RuntimeException {
    public BreakStatement() {
        super(null, null, false, false);
    }
}
