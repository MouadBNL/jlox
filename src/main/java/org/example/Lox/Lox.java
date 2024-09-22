package org.example.Lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Lox {
    static Boolean hadError = true;
    public static void run(String src) {
        Scanner scanner = new Scanner(src);
        List<Token> tokens = scanner.scanTokens();

        for(Token token: tokens) {
            System.out.println(token);
        }
    }
    public static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
        if(hadError) System.exit(65);
    }
    public static void runPrompt() throws IOException {
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


    public static void error(int line, String message) {
        report(line, "", message);
    }

    public static void report(int line, String where, String message) {
        System.err.println("[Line "+ line +"] Error " + where + ": " + message);
        hadError = true;
    }
}
