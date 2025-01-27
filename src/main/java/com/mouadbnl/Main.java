package com.mouadbnl;

import com.mouadbnl.Lox.Lox;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        //Lox.demo();
        var loxInstance = new Lox();
        if (args.length > 1) {
            System.out.println("Usage: jlox [script]");
            System.exit(64);
        } else if (args.length == 1) {
            System.out.println("Executing script: " + args[0]);
            loxInstance.runFile(args[0]);
        } else {
            loxInstance.runPrompt();
        }
    }
}