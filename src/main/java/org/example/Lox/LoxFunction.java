package org.example.Lox;

import org.example.Lox.Exception.Return;

import java.util.List;

public class LoxFunction implements LoxCallable {
    private final Stmt.Function declaration;
    private final Environment closure;

    public LoxFunction(Stmt.Function declaration, Environment closure) {
        this.declaration = declaration;
        this.closure = closure;
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        Environment environment = new Environment(closure);
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

    public LoxFunction bind(LoxInstance loxInstance) {
        Environment environment = new Environment(closure);
        var thisKeyword =new Token(TokenType.THIS, "this", null, 0);
        environment.define(thisKeyword, loxInstance);
        return new LoxFunction(declaration, environment);
    }
}
