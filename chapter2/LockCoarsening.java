public class LockCoarsening {

    public static void main(String[] args) {
        synchronized (Class1.class) {
            ....
        }
        synchronized (Class1.class) {
            ....
        }
        synchronized (Class2.class) {
            ....
        }
    }
}
