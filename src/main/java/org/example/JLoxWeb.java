package org.example;

import org.example.Lox.Lox;
import org.teavm.jso.JSExport;

public class JLoxWeb {

    @JSExport
    public static void runlox(String src) {
        Lox.run(src);
    }
}
