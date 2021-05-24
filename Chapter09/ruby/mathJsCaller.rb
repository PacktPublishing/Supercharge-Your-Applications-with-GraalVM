
arrayPy  = Polyglot.eval("python", "[10, 10.23456, 'Array String element']")
puts arrayPy.to_s

lengthOfArray = arrayPy.size
puts "Iterating throught the Python Array object of size " + lengthOfArray.to_s

for i in 0..lengthOfArray - 1
    puts "Element at " + i.to_s + " is " + arrayPy[i].to_s
end

Polyglot.eval_file("./math.js") 
message = Polyglot.import("message")
addFunction = Polyglot.import_method("add")
subtractFunction = Polyglot.import_method("subtract")
multiplyFunction = Polyglot.import_method("multiply")

puts "Message from JS " + message
puts "Result of add(10,20) " + add(10,20).to_s
puts "Result of subtract(10,20) " + subtract(40,20).to_s
puts "Result of multiply(10,20) " + multiply(10,20).to_s



