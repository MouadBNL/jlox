package org.example.Lox;

import org.example.Lox.Exception.RuntimeError;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    final Environment enclosing;
    private final Map<String, Object> values = new HashMap<>();

    public Environment() {
        enclosing = null;
    }

    public Environment(Environment enclosing) {
        this.enclosing = enclosing;
    }

    public void define(Token name, Object value) {
        if(values.containsKey(name.lexeme)) {
            throw new RuntimeError(name, "Trying to define "+name.lexeme+" variable twice.");
        }
        values.put(name.lexeme, value);
    }

    public Object get(Token name) {
        if(values.containsKey(name.lexeme)){
            return values.get(name.lexeme);
        }

        if(enclosing != null) {
            return enclosing.get(name);
        }

        throw new RuntimeError(name, "Undefined variable " + name.lexeme + ".");
    }

    public void assign(Token name, Object value) {
        if(values.containsKey(name.lexeme)) {
            values.put(name.lexeme, value);
            return;
        }
        if(enclosing != null) {
            enclosing.assign(name, value);
            return;
        }
         throw new RuntimeError(name, "Undefined variable " + name.lexeme + ".");
    }

    public Object getAt(Token name, Integer distance) {
        return ancestor(distance).values.get(name.lexeme);
    }

    Environment ancestor(int distance) {
        Environment environment = this;
        for (int i = 0; i < distance; i++) {
            assert environment != null;
            environment = environment.enclosing;
        }

        return environment;
    }

    public void assignAt(Integer distance, Token name, Object value) {
        ancestor(distance).values.put(name.lexeme, value);
    }
}
