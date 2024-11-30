package org.example.Lox;

import java.util.List;

public class AstPrinter implements Expr.Visitor<String> {

    String print(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public String visitAssignExpr(Expr.Assign expr) {
        return parenthesize("assign", expr, expr.value);
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return parenthesize(expr.operator.lexeme,
                expr.left, expr.right);
    }

    @Override
    public String visitCallExpr(Expr.Call expr) {
        return "(" +
                "call " +
                expr.callee.accept(this) +
                ", " +
                parenthesize("args", expr.arguments.toArray(Expr[]::new)) +
                ")";
    }

    @Override
    public String visitGetExpr(Expr.Get expr) {
        return "("
                + "get " + expr.name.lexeme
                + " from" + expr.object.accept(this)
                + ")";
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return parenthesize("group", expr.expression);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null) return "nil";
        return expr.value.toString();
    }

    @Override
    public String visitLogicalExpr(Expr.Logical expr) {
        return parenthesize(expr.operator.lexeme,
                expr.left, expr.right);
    }

    @Override
    public String visitSetExpr(Expr.Set expr) {return "("
            + "set " + expr.name.lexeme
            + " from" + expr.object.accept(this)
            + " to" + expr.value.accept(this)
            + ")";
    }

    @Override
    public String visitSuperExpr(Expr.Super expr) {
        return "super";
    }

    @Override
    public String visitThisExpr(Expr.This expr) {
        return "this";
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return parenthesize(expr.operator.lexeme, expr.right);
    }

    @Override
    public String visitVariableExpr(Expr.Variable expr) {
        return null;
    }

    private String parenthesizeList(String name, List<Expr> exprs) {
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for (Expr expr : exprs) {
            builder.append(" ");
            builder.append(expr.accept(this));
        }
        builder.append(")");

        return builder.toString();
    }
    private String parenthesize(String name, Expr... exprs) {
        return parenthesizeList(name, List.of(exprs));
    }
}
