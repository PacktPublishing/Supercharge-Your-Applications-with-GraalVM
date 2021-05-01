

function callNumPyExmple() {
    Polyglot.evalFile('python', './numpy-example.py');
    heartAnalysis = Polyglot.import('heartAnalysis');
    result = heartAnalysis();
    return result;
}

result = callNumPyExmple();
print ('Average age of people who are getting level 3 and greater chest pain : '+  String(result));