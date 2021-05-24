import java.io.File;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
public class NumPyJavaExample {
    public void callPythonMethods() {
        Context ctx = Context.newBuilder().allowAllAccess(true).build();
        try {
            File fibCal = new File("./numpy-example.py");
            ctx.eval(Source.newBuilder("python", fibCal).build());
            
            Value sortArrayFn = ctx.getBindings("python").getMember("sortArray");
            Value hearAnalysisFn = ctx.getBindings("python").getMember("heartAnalysis");

            int[] unsorted = {499,200,549,2,3,567,44,8,222,9,5,44,7,78,22,45,2212,567,34,4556,87,44};
            Value result = sortArrayFn.execute(unsorted);
            System.out.println("Sorted array returned from Python :" + result.toString());

            Value heartAnalysisReport = hearAnalysisFn.execute();
            System.out.println("Average age of people who are getting level 3 and greater chest pain :" + heartAnalysisReport.toString());

        }   catch (Exception e) {
            System.out.println("Exception : " );
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        NumPyJavaExample obj = new NumPyJavaExample();
        obj.callPythonMethods();
    }
}
