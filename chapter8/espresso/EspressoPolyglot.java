import com.oracle.truffle.espresso.polyglot.Polyglot;
public class EspressoPolyglot {
    public static void main(String[] args) {
        try {
            Polyglot.eval("js", "print('Hello from JS on Espresso');");
            Object mathjs = Polyglot.eval("js", "print('Hello there from JS on Espresso');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}