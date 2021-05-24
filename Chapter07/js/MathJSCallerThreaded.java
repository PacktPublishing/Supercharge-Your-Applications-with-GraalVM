import java.io.File;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

public class MathJSCallerThreaded {

    public void runMathJS() {
        Context ctx = Context.create("js");
        try {
            File mathJSFile = new File("./math.js");
            ctx.eval(Source.newBuilder("js", mathJSFile).build());

            Value addFunction = ctx.getBindings("js").getMember("add");
            Value subtractFunction = ctx.getBindings("js").getMember("subtract");
            Value multiplyFunction = ctx.getBindings("js").getMember("multiply");
            Value helloMathMessage = ctx.getBindings("js").getMember("helloMathMessage");

            System.out.println("Binding Keys :" + ctx.getBindings("js").getMemberKeys());

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        /* Solution 1
                        Context ctx = Context.create("js");
                        ctx.eval(Source.newBuilder("js", mathJSFile).build());

                        Value addFunction = ctx.getBindings("js").getMember("add");
                        Value subtractFunction = ctx.getBindings("js").getMember("subtract");
                        Value multiplyFunction = ctx.getBindings("js").getMember("multiply");
                        Value helloMathMessage = ctx.getBindings("js").getMember("helloMathMessage");
                        */

                        // Solution 2
                        
                        while (true) {
                            synchronized(ctx) {
                                Integer addResult = addFunction.execute(30, 20).asInt();
                                Integer subtractResult = subtractFunction.execute(30, 20).asInt();
                                Integer multiplyResult = multiplyFunction.execute(30, 20).asInt();
                            }
                            // System.out.println(("Add Result "+ addResult+ " Subtract Result "+
                            // subtractResult+ " Multiply Result "+ multiplyResult));
                            // System.out.println("helloMathMessage : " + helloMathMessage.toString());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();

            // Simulating the concurrent access by accessing continously.
            while (true) {
                //If you do not put this in synchronized block, it will throw java.lang.IllegalStateException exception
                synchronized(ctx) { 
                    Integer addResult = addFunction.execute(30, 20).asInt();
                    Integer subtractResult = subtractFunction.execute(30, 20).asInt();
                    Integer multiplyResult = multiplyFunction.execute(30, 20).asInt();
                }
                // System.out.println(("Add Result "+ addResult+ " Subtract Result "+
                // subtractResult+ " Multiply Result "+ multiplyResult));
                // System.out.println("helloMathMessage : " + helloMathMessage.toString());
            }

        } catch (Exception e) {
            System.out.println("Exception : ");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MathJSCallerThreaded obj = new MathJSCallerThreaded();
        obj.runMathJS();
    }

}
