
import java.io.File;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

public class FibonacciCalculatorLLVMEmbed {
    public static void main(String[] args) {
        try {
            Context polyglot = Context.newBuilder().allowAllAccess(true).build();
            File file = new File("fibpoly");
            Source source = Source.newBuilder("llvm", file).build();
            Value fibpoly = polyglot.eval(source);
            Value fibFunc = fibpoly.getMember("fib");
            
            //fibpoly.execute();
            Value ret = fibFunc.execute(20);

            System.out.println("Returned value to Java "+ ret.asLong());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
