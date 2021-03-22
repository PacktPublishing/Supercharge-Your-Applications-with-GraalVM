fibclass = Java.type('FibonacciCalculator')
fibObject = fibclass.new

fibonacciArray = fibObject.findFibonacci(10)

for i in 0..fibonacciArray.size - 1
    puts "Element at " + i.to_s + " is " + fibonacciArray[i].to_s
end

puts "Calling iterateFibonacci()"

fibObject.iterateFibonacci()