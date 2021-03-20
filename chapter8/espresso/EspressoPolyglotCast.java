import com.oracle.truffle.espresso.polyglot.Polyglot;
import com.oracle.truffle.espresso.polyglot.Interop;

public class EspressoPolyglotCast {
    public static void main(String[] args) {
        try {
            Object stringObject = Polyglot.eval("js", "'This is a JavaScript String'");
            Object integerObject = Polyglot.eval("js", "1000");
            Object doubleObject = Polyglot.eval("js", "10.12345");
            Object arrayObject = Polyglot.eval("js", "[1234, 10.2233, 'String element',400,500,'Another Sttring element']");
            Object booleanObject = Polyglot.eval("js", "10 > 5");
            
            String localStringObject = Polyglot.cast(String.class, stringObject);
            Integer localIntegerObject = Polyglot.cast(Integer.class, integerObject);
            Double localDoubleObject = Polyglot.cast(Double.class, doubleObject);
            //Integer[] localIntArrayObject = Polyglot.cast(Integer[].class, intArrayObject);
            Boolean localBooleanObject = Polyglot.cast(Boolean.class, booleanObject);

            System.out.println("\nString Object : "+ localStringObject 
                                        + ", \nInteger : "+ localIntegerObject
                                        + ", \nDouble : "+ localDoubleObject
                                        + ", \nBoolean : "+ localBooleanObject);     

            long sizeOfArray = Interop.getArraySize(arrayObject);
            System.out.println("\n Array of objects with Size : " + sizeOfArray );
            
            for (int i=0; i<sizeOfArray; i++) {
                Object currentElement = Interop.readArrayElement(arrayObject, i);
                if (Interop.fitsInInt(currentElement)) {
                    System.out.println("Interger Element: " + Interop.asInt(currentElement));
                }
                if (Interop.fitsInDouble(currentElement)) {
                    System.out.println("Double Element: " + Interop.asDouble(currentElement));
                }
                if (Interop.isString(currentElement)) {
                    System.out.println("String Element: " + Interop.asString(currentElement));
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
