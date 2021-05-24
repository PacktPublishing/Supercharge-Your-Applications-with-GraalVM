import java.io.File;
import java.math.BigDecimal;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

public class MathEngineExample {
    public void evaluateExpression(String exp) {
        Context ctx = Context.newBuilder().allowAllAccess(true).build();
        try {
            File fibCal = new File("./math_engine_expression.rb");
            ctx.eval(Source.newBuilder("ruby", fibCal).build());
            Value evaluateFunction = ctx.getBindings("ruby").getMember("eval");
            Double evaluatedValue = evaluateFunction.execute(exp).asDouble();
            System.out.printf("Evaluated Expression : " + evaluatedValue.toString());  
        }   catch (Exception e) {
            System.out.println("Exception : " );
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        MathEngineExample obj = new MathEngineExample();
        obj.evaluateExpression("20 * (3/2) + (5 * 5) / (100.5 * 3)");
    }
    
}
