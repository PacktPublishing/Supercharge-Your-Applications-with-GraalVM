
var helloMathMessage = " Hello Math.js";

function add(a, b) {
    print("message from js: add() called");
    return a+b;
}

function subtract(a, b) {
    print("message from js: subtract() called");
    return a-b;
}

function multiply(a, b) {
    print("message from js: multiply() called");
    return a*b;
}

function square(a) {
    print("message from js: square() called");
    Polyglot.evalFile('python', './mathUtil.py');
    pySquare = Polyglot.import('square');
    result = pySquare(a)
    return result
}

Polyglot.export('add', add);
Polyglot.export('subtract', subtract);
Polyglot.export('multiply', multiply);
Polyglot.export('square', square);
Polyglot.export('message', helloMathMessage)