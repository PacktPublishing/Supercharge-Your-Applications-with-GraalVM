public class EscapeAnalysis2 {

    public void createNumberofObjects(int numberOfArraysToCreate, int numberOfCellsInArray) {
        for (int i=0; i< numberOfArraysToCreate; i++) {
            allocateObjects(numberOfCellsInArray);
        }
    }

    private void allocateObjects(int numberOfCellsInArray) {
       
        int[] arrayObj = new int[numberOfCellsInArray];

        for (int i=0; i< numberOfCellsInArray; i++) {
            //Heap allocation, which could have been easily a local stack allocation
            Integer dummyInt = new Integer(i);
            arrayObj[i] = dummyInt.intValue();
        }

        return;

    }

    public static void main(String[] args) {
        EscapeAnalysis2 obj = new EscapeAnalysis2();
        obj.createNumberofObjects(100000, 10);
    }
    
}
