public class OSRTest {
    public static void main(String[] args) {

        int total = 0;
        //long running loop
        for(int i=0; i < 10000000; i++) {
            
            //Perfom some function
            total++;
        }
        System.out.println("Total number of times is "+ total);
    }
}
