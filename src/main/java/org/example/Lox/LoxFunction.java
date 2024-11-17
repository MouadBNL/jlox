package org.example.Lox;

import org.example.Lox.Exception.Return;

import java.util.List;

public class LoxFunction implements LoxCallable {
    private final Stmt.Function declaration;
    public LoxFunction(Stmt.Function declaration) {
        this.declaration = declaration;
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        Environment environment = new Environment(interpreter.globals);
        for(int i = 0; i < this.arity(); i++) {
            environment.define(declaration.params.get(i), arguments.get(i));
        }
        try {
            interpreter.executeBlock(declaration.body, environment);
        } catch (Return returnValue) {
            return returnValue.getValue();
        }
        return null;
    }

    @Override
    public int arity() {
        return declaration.params.size();
    }

    @Override
    public String toString() {
        return "<fn " + declaration.name.lexeme + ">";
    }
}
