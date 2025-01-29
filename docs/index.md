# JLox
JLox (Java lox) is my own implementation of the lox programming language based on the crafting interpreters book.
It is a dynamically typed language with a syntax similar to C/Javascript with support for variables, control flow, functions classes and automatic memory management through garbage collection.
Although it relies on many things that Java provides such as memory management, it did help me a lot in understanding how a lot of thins work such as closures, operator precedence and a lot more

### Goal
My goal for now is to create a web interface that will allow users to experiment with JLox. Basically having the user write a script on the interface, and be able to feed it to lox and produce the output.
Down the line, I want to improve this to include some sort of debugging tools, such as stop on a line, inspect variable content, and even visualize the AST generated.


### Api
In order to run JLox on the web, first I need to have some sort of clear API to communicate with
```ts
/**
 * Jlox Api experimentation
 */

const ui: any = null;

const lox: any = new Jlox();
lox.on("read", async (msg: string) => {
    const input = await ui.read(msg);
    return input;
    // ui.on("user-input", (input: string) => {
    //   res(input);
    // });
});

lox.on("print", (output: string) => {
    console.log("[LOX] stdout: " + output);
});

lox.on("error", (msg: string) => {
    console.log("[LOX] error: " + msg);
});

const { stdout, stderr, ast, environment } = await lox.run(`
  var a = 5;
  var b = a + 9;
  print b;
`);

console.log("[Lox] STDOUT: ", stdout); // "14"
console.log("[Lox] STDERR: ", stderr); // ""
console.log("[Lox] AST: ", ast); // {program: [{type: "declaration", ...}]}
console.log("[Lox] ENV: ", environment); // [{token: {}, identifier, "a", type: "number", value: 5, constant: false}]
```