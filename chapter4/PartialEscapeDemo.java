

public class PartialEscapeDemo {

    public String partialEscapeDemoMethod(boolean flag, String message) {
        String printMessage = new String();
        if (flag) {
            printMessage = "Hello " + message;
        } else {
            String prefixMessage = new String();
            prefixMessage = "Hi ";
            printMessage = prefixMessage + message;
        }
        return printMessage;
    }

    public static void main(String[] args) {
        PartialEscapeDemo obj = new PartialEscapeDemo();
        while (true) {
            String wish = obj.partialEscapeDemoMethod(true, " Partial Escape Analysis");
            System.out.println(wish);
        }
     }
    
}

public void method(boolean flag) {
    Class1 object1 = new Class1();

    Class2 object2 = new Class2();
    
    object1.parameter = value;

    if(flag) {
        return object1;
    }
    return object2;
}




public class OptionalDouble {
    public double getAsDouble() {
      if (!isPresent) {
        throw new NoSuchElementException("No value present");
      }
      return value;
    }
}