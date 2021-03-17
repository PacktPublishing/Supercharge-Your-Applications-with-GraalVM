import java.io.File;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
public class MathUtilPyCaller {
    public void runFibCalPy() {
        long startTime = System.currentTimeMillis();
        Context ctx = Context.newBuilder().allowAllAccess(true).build();
        try {
            File fibCal = new File("./mathUtil.py");
            ctx.eval(Source.newBuilder("python", fibCal).build());
            //ctx.
            Value squareFunction = ctx.getBindings("python").getMember("square");
            long now = 0;
            long last = startTime;
            for (int i = 100; i < 105; i++) {
                Integer squareVal = squareFunction.execute(i).asInt();
                now = System.currentTimeMillis();
                System.out.printf("%d (%d ms)%n", squareVal , now - last);
                last = now;
            }
            long endTime = System.currentTimeMillis();
            System.out.printf("total: (%d ms)%n", System.currentTimeMillis() - startTime);  
        }   catch (Exception e) {
            System.out.println("Exception : " );
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MathUtilPyCaller obj = new MathUtilPyCaller();
        obj.runFibCalPy();
    }
    
}
