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