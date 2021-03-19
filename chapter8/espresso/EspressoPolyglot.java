
import com.oracle.truffle.espresso.polyglot.Polyglot;

public class EspressoPolyglot {
    public static void main(String[] args) {

        Object mathjs = Polyglot.eval("js", "print('Hello Espresso Polyglot');");
        
    }
    
}
