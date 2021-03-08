public class AddGraph {

    public int addNumber(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        AddGraph obj = new AddGraph();
        for(int i=0; i<2000000; i++) {
            int value = obj.addNumber(10+i, 20+i);
            value = value+ i;

        }
        System.out.println(obj.addNumber(10, 20));
    }
    
}
