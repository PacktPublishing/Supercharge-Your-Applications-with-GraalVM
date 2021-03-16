import polyglot

polyglot.eval(path="./math.js", language="js")
add = polyglot.import_value('add')
subtract = polyglot.import_value('subtract')
multiply = polyglot.import_value('multiply')
message = polyglot.import_value('message')
square = polyglot.import_value('square')

print ("Adding numbers by calling JS: " + str(add(10, 20)))
print ("Subtracting numbers by calling JS: " + str(subtract(50, 20)))
print ("Multiplying numbers by calling JS: " + str(multiply(10, 20)))

print ("Square numbers by calling JS->Python: " + str(square(10, 20)))

print ("Hello messagr from JS: " + message)