var express = require('express');
var app = express();

app.get('/', function(request, response) {
    var responseString = "<h1>Hello Graal Node </h1>";
    response.send(responseString);
});

app.get('/poly', function(request, response) {

    var responseString = "<h1>Hello Graal Polyglot </h1>";
    var array = Polyglot.eval("python", "[1,2,3,4, 100, 200, 300, 400]")

    responseString = responseString + "<ul>";
    for (let index = 0; index < array.length; index++) {
        responseString = responseString + "<li>";
        responseString = responseString + array[index];
        responseString = responseString + "</li>";
    }
    responseString = responseString + "</ul>";
    response.send(responseString);
});

app.get('/fibonacci', function(request, response) {
    var fibonacciCalculatorClass = Java.type("FibonacciCalculatorPolyglot");
    var fibonacciCalculatorObject = new fibonacciCalculatorClass();
    //fibonacciCalculatorClass.class.static.main([""]);
    var array = fibonacciCalculatorObject.findFibonacci(10);
    
    var responseString = "<h1>Hello Graal Polyglot - Fibonacci numbers </h1>";
    responseString = responseString + "<ul>";
    for (let index = 0; index < array.length; index++) {
        responseString = responseString + "<li>";
        responseString = responseString + array[index];
        responseString = responseString + "</li>";
    }
    responseString = responseString + "</ul>";
    response.send(responseString);
});


app.get('/threading', function(request, response) {
    var fibonacciCalculatorClass = Java.type("FibonacciCalculatorPolyglotMultithreading");
    var fibonacciCalculatorObject = new fibonacciCalculatorClass();
    //fibonacciCalculatorClass.class.static.main([""]);
    var array = fibonacciCalculatorObject.totalFibonacci(10);
    
    var responseString = "<h1>Hello Graal Polyglot - Fibonacci numbers </h1>";
    responseString = responseString + "<ul>";
    for (let index = 0; index < array.length; index++) {
        responseString = responseString + "<li>";
        responseString = responseString + array[index];
        responseString = responseString + "</li>";
    }
    responseString = responseString + "</ul>";
    response.send(responseString);
});



app.listen(8080, function() {
    console.log('Started the server at 8080')
});