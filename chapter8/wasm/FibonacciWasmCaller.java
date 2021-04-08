import java.io.File;
import java.nio.file.Files;

import org.graalvm.polyglot.*;
import org.graalvm.polyglot.io.ByteSequence;

public class FibonacciWasmCaller {
    public static void main(String[] args) {
        try {
            File file = new File("fibonacci.wasm");
            byte[] wasmBinaryArray = Files.readAllBytes(file.toPath());
            Context.Builder contextBuilder = Context.newBuilder("wasm");
            contextBuilder.option("wasm.Builtins", "wasi_snapshot_preview1");
            
            Source.Builder sourceBuilder = Source.newBuilder("wasm", ByteSequence.create(wasmBinaryArray), "fibonacci");
            Source source = sourceBuilder.build();
            Context context = contextBuilder.build();

            context.eval(source);

            Value bindings = context.getBindings("wasm");
            System.out.println((bindings.getMemberKeys()));

            Value main = bindings.getMember("main");
            //main.execute();
            System.out.println(main.canExecute());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
