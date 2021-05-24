public class FibonacciCalculator2{  
    public long findFibonacci(int count) {
        int fib1 = 0;
        int fib2 = 1;
        int currentFib, index;
        long total = 0;
        for(index=2; index < count; ++index ) {    
            currentFib = fib1 + fib2;    
            fib1 = fib2;    
            fib2 = currentFib;    
            total += currentFib;
        }
        return total;
    }

    public static void main(String args[])  
    {    
        FibonacciCalculator2 fibCal = new FibonacciCalculator2();
        long startTime = System.currentTimeMillis();
        long now = 0;
        long last = startTime;
        for (int i = 1000000000; i < 1000010000; i++) {
            fibCal.findFibonacci(i);
            now = System.currentTimeMillis();
            System.out.printf("%d (%d ms)%n", i , now - last);
            last = now;
        }
        long endTime = System.currentTimeMillis();
        System.out.printf(" total: (%d ms)%n", System.currentTimeMillis() - startTime);  
      
    }
}
