package org.example;

import org.example.Lox.Lox;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Lox.demo();
        if (args.length > 1) {
            System.out.println("Usage: jlox [script]");
            System.exit(64);
        } else if (args.length == 1) {
            Lox.runFile(args[0]);
        } else {
            Lox.runPrompt();
        }
    }
}