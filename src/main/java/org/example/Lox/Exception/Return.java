package org.example.Lox.Exception;

public class Return extends RuntimeException {
    final Object value;

    public Return(Object value) {
        super(null, null, false, false);
        this.value = value;
    }

    public Object getValue() {
        return this.value;
    }
}
