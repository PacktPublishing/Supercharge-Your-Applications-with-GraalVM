import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

public class HelloRPolyglot {
    public String hello(String name) {
        System.out.println("Hello Welcome from hello");
        return "Hello Welcome from hello " + name;
    }

    public static void helloStatic() {
        System.out.println("Hello from Static heloo()");
        try {
            Context polyglot = Context.create();
            Value array = polyglot.eval("js", "print('Hello from JS inline in HelloRPolyglot class')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        HelloRPolyglot.helloStatic();
    }
}
