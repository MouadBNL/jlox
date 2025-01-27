package com.mouadbnl.Lox.Exception;

import com.mouadbnl.Lox.Token;

public class Return extends RuntimeException {
    final Object value;
    final Token token;

    public Return(Object value, Token token) {
        super(null, null, false, false);
        this.value = value;
        this.token = token;
    }

    public Object getValue() {
        return this.value;
    }

    public Token getToken() {
        return token;
    }
}
