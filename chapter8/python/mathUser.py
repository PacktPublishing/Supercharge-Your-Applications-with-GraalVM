import polyglot

polyglot.eval(path="./math.js", language="js")

message = polyglot.import_value('message')
square = polyglot.import_value('square')

print ("Square numbers by calling JS->Python: " + str(square(10, 20)))
print ("Hello message from JS: " + message)