import java.io.File;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

public class ManagedLLVM {
    public static void main(String[] args) {
        try {
            Context polyglot = Context.newBuilder().allowAllAccess(true).option("llvm.managed", "true").build();
            File file = new File("managedLLVM");
            Source source = Source.newBuilder("llvm", file).build();
            Value mllvm = polyglot.eval(source);
            mllvm.execute();
        } catch (Exception e) {
            System.out.println("Exception occured....");
            e.printStackTrace();
        }
    }
}
