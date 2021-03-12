import java.io.File;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

public class FibCalPyCaller {

    public void runFibCalPy() {
        Context ctx = Context.create("python");
        try {
            File fibCal = new File("./FibCal.js");
            ctx.eval(Source.newBuilder("python", fibCal).build());
            ctx.

            Value addFunction = ctx.getBindings("js").getMember("add");
            Value subtractFunction = ctx.getBindings("js").getMember("subtract");
            Value multiplyFunction = ctx.getBindings("js").getMember("multiply");
            Value helloMathMessage = ctx.getBindings("js").getMember("helloMathMessage");

            System.out.println("Binding Keys :" + ctx.getBindings("js").getMemberKeys());

            Integer addResult = addFunction.execute(30, 20).asInt();
            Integer subtractResult = subtractFunction.execute(30, 20).asInt();
            Integer multiplyResult = multiplyFunction.execute(30, 20).asInt();

            System.out.println(("Add Result "+ addResult+ " Subtract Result "+ subtractResult+ " Multiply Result "+ multiplyResult));
            System.out.println("helloMathMessage : " + helloMathMessage.toString());


        }   catch (Exception e) {
            System.out.println("Exception : " );
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MathJSCaller obj = new MathJSCaller();
        obj.runMathJS();
    }
    
}
