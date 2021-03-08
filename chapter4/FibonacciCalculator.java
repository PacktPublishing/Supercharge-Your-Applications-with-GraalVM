//package com.packt.ebook.graalvm.chapter4;

class FibonacciCalculator{  

    public int[] findFibonacci(int count) {
        int fib1 = 0;
        int fib2 = 1;

        int currentFib, index;

        
        int [] fibNumbersArray = new int[count];
          
        for(index=2; index < count; ++index ) {    
            
            currentFib = fib1 + fib2;    
            
            fib1 = fib2;    
            fib2 = currentFib;    

            fibNumbersArray[index - 1] = currentFib;

        }    

        return fibNumbersArray;
   
    }

    public static void main(String args[])  
    {    
        FibonacciCalculator fibCal = new FibonacciCalculator();
        
        

        for (int i = 1000000; i < 1000000000; i++) {
            long startTime = System.currentTimeMillis();
            fibCal.findFibonacci(i);

            long endTime = System.currentTimeMillis();
            long timeLapse = (endTime - startTime)/1000;
            
            System.out.println(" Sequence for " + i + " Fibonacci numbers in " + timeLapse);    
            
        }
      
    }
}