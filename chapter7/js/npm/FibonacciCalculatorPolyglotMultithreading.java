
import org.graalvm.polyglot.*;
import org.graalvm.polyglot.proxy.*;

public class FibonacciCalculatorPolyglotMultithreading {

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

    public void totalFibonacci() {
        long startTime = System.currentTimeMillis();
        long now = 0;
        long last = startTime;
        
        for (int i = 1000000000; i < 1000000010; i++) {
            int[] fibs = findFibonacci(i);
            
            long total = 0;
            for (int j=0; j<fibs.length; j++) {
                total += fibs[j];
            }

            now = System.currentTimeMillis();
            System.out.printf("%d (%d ms)%n", i , now - last);
            last = now;
        }


        long endTime = System.currentTimeMillis();
        long totalTime = System.currentTimeMillis() - startTime;

        System.out.printf("total: (%d ms)%n", totalTime);  
    }

    public static void main(String args[])  
    {    
        FibonacciCalculatorPolyglotMultithreading fibCal = new FibonacciCalculatorPolyglotMultithreading();
        fibCal.totalFibonacci();
    }
}
