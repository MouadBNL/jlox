package com.mouadbnl.Lox.StreamBuffers;

public class StandardErrorBuffer {
    String content = "";

    public void pushLine(String line) {
        this.content = this.content + line + "\n";
    }

    public String flush() {
        var content = this.content;
        this.content = "";
        // System.out.print("[StandardErrorBuffer] flush: ");
        System.err.print(content);
        return content;
    }
}
