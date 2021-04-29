import java.io.File;

abstract class Node {
    // Executes the operation encoded by this
    // node and returns the result.
    public abstract Object execute(Frame f);

    // Link to the parent node and utility to
    // replace a node in the AST.
    private Node parent;

    protected void replace(Node newNode);
}

    Context ctx = Context.newBuilder().allowAllAccess(true).build();
    File path = new File("/path/to/scriptfile");
    Source pythonScript = Source.newBuilder("python", new File(path, "pythonScript.py")).build();
    ctx.eval(pythonScript)





    @Specialization protected long executeAddInt (int left, int right) {
        return left + right;
    }
    @Specialization String executeAddFloat (Float left, Float right) {
        return left + right;
    }
    @Specialization String executeAddString (String left, String right) {
        return left + right;
    }
    @Fallback protected void typeError (Object left, Object right) {
        throw new TypeException("type error: args must be two integers or floats or two", this);
    }


    @Specialization(guards = {"!isInteger(operand)", "!isFloat(operand)"})
    protected final int executeTheMethod(final Object operand) {
      //....code to execute if the expression is true
    }



@Specialization(rewriteOn = ArithmeticException.class)
 int executeNoOverflow(int a, int b) {
     return Math.addExact(a, b);
 }
 @Specialization
 long executeWithOverflow(int a, int b) {
     return a + b;
 }

 