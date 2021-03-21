var fibpoly = Polyglot.evalFile("wasm" , "fibonacci.wasm");
var fib = fibpoly.fib(20);
print("Returned value to JS: "+ fib);