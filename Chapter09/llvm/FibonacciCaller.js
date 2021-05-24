var fibpoly = Polyglot.evalFile("llvm" , "fibpoly");
var fib = fibpoly.fib(20);
print("Returned value to JS: "+ fib);