public interface Shape {
    String whichShapeAreYou();
}

public class Circle implements Shape {
    public String whichShapeAreYou() { return "I am Circle";} 
}

public class Square implements Shape {
    public String whichShapeAreYou() { return "I am Square";} 
}

public class Triangle implements Shape {
    public String whichShapeAreYou() { return "I am Triangle";} 
}

public static void main(String[] args) {
	//Some code and logic here

    switch (circleType) {
        case 0:
            shape = new Circle();
            break;
        case 1:
            shape = new Square();
            break;
        case 2:
            shape = new Triangle();
            break;
    
        default:
            System.out.println("Invalid shape");
            break;
    }
}
