

public class DemonstrateGraalGraph {

    public long calculateResult() {
        long result = 0;
        for (int i=0; i<2000; i++) {
            result = result + i;
        }
        return result;
    }

    public static void main(String[] args) {
        DemonstrateGraalGraph obj = new DemonstrateGraalGraph();
        while (true) {
            long result = obj.calculateResult();
            System.out.println("Total: " + result);
        }
    }
    
}
