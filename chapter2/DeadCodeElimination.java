/**
 * DeadCodeElimination
 */
public class DeadCodeElimination {
    public void calculateSomething() {

        //System.out.println("Do some activity here");

        int[] arrayOfValues = new int[1000000];
    
        int finalTotalValue = 0;
    
        for (int i=0; i< arrayOfValues.length; i++) {
            finalTotalValue = calculateValue(arrayOfValues[i]);
        }


        //System.out.println("Do some more  activity here, but never use final Total count");
    }
    
    public int calculateValue(int value) {
        //use some formula to calucalte the value
        return value * value;
    }


    public static void main(String[] args) {
        DeadCodeElimination obj = new DeadCodeElimination();
        for (int i=0; i< 10000; i++) {
            obj.calculateSomething();
        }
    }
    
}


