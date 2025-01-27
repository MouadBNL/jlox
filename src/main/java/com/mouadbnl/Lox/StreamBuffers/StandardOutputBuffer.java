package com.mouadbnl.Lox.StreamBuffers;

public class StandardOutputBuffer {
    String content = "";

    public void pushLine(String line) {
        this.content = this.content + line + "\n";
    }

    public String flush() {
        var content = this.content;
        this.content = "";
        // System.out.print("[StandardOutputBuffer] flush: ");
        System.out.print(content);
        return content;
    }
}
