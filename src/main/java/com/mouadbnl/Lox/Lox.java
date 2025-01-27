package com.mouadbnl.Lox;

import com.mouadbnl.Lox.StreamBuffers.StandardErrorBuffer;
import com.mouadbnl.Lox.StreamBuffers.StandardOutputBuffer;
import com.mouadbnl.Lox.Exception.RuntimeError;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Lox {
    public Interpreter interpreter;
    public Boolean hadError = false;
    public Boolean hadRuntimeError = false;
    public StandardOutputBuffer stdout;
    public StandardErrorBuffer stderr;

    public Lox() {
        this.interpreter = new Interpreter(this);
        stdout = new StandardOutputBuffer();
        stderr = new StandardErrorBuffer();
    }

    public void run(String src) {
        Scanner scanner = new Scanner(src, this);
        List<Token> tokens = scanner.scanTokens();
        Parser parser = new Parser(tokens, this);
        List<Stmt> statements = parser.parse();
        if(hadError) return;

        Resolver resolver = new Resolver(interpreter, this);
        resolver.resolve(statements);
        if (hadError) return;

        interpreter.interpret(statements);

    }
    public void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
        if(hadError) System.exit(65);
        if(hadRuntimeError) System.exit(70);
    }
    public void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        System.out.println("Lox interpreter (Ctrl + D to exit)");
        for(;;) {
            System.out.print("> ");
            String line = reader.readLine();
            if(line == null) break;
            run(line);
            hadError = false;
        }
    }


    public void error(int line, String message) {
        report(line, "", message);
    }

    public void error(Token token,String message) {
        if(token.type == TokenType.EOF) {
            report(token.line, ", at end", message);
        } else {
            report(token.line, "at '" + token.lexeme + "'", message);
        }
    }

    public void report(int line, String where, String message) {
        stderr.pushLine("[Line "+ line +"] Error " + where + ": " + message);
        stderr.flush();
        //System.err.println("[Line "+ line +"] Error " + where + ": " + message);
        hadError = true;
    }

    public static void demo() {
        Expr expr = new Expr.Binary(
                new Expr.Unary(
                        new Token(TokenType.MINUS, "-", null, 1),
                        new Expr.Literal(123),
                        false
                ),
                new Token(TokenType.STAR, "*", null, 1),
                new Expr.Grouping(
                        new Expr.Literal(78.6)
                )
        );

        var printer = new AstPrinter();
        System.out.println(printer.print(expr));
    }

    public void runtimeError(RuntimeError error) {
        stderr.pushLine(error.getMessage() + "\n[line " + error.token.line + "]");
        stderr.flush();
        //System.err.println(error.getMessage() + "\n[line " + error.token.line + "]");
        hadRuntimeError = true;
    }
}
