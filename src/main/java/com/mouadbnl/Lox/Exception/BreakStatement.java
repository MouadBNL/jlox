package com.mouadbnl.Lox.Exception;

import com.mouadbnl.Lox.Token;

public class BreakStatement extends RuntimeException {
    final Token token;
    public BreakStatement(Token token) {
        super(null, null, false, false);
        this.token = token;
    }
    public Token getToken() {
        return token;
    }
}
