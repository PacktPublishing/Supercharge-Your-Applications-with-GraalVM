public class Sample {
    public static void main(String[] args) {
        Sample samp = new Sample();
        while (true) {
        for(int i=0; i<1000000; i++) {
            samp.performOperation();
        }
    }
    }

    public void performOperation() {
        int sum = 0;
        int x = 100;
        performAnotherOperation();

    }

    public void performAnotherOperation() {
        int a = 100;
        int b = 200;
        for(int i=0; i<1000000; i++) {
            int x = a + b;
            int y = (24*25) + x;
            int z = (24*25) + x;
        }
    }
}