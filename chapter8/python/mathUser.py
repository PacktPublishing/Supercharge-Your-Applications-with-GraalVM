import polyglot

polyglot.eval(path="./math.js", language="js")

message = polyglot.import_value('message')
square = polyglot.import_value('square')

print ("Square numbers by calling JS->Python: " + str(square(10, 20)))
<<<<<<< HEAD
print ("Hello message from JS: " + message)
=======
print ("Hello messagr from JS: " + message)
>>>>>>> 95ff00014f1943335ea0028bfb2ce890ba962311
