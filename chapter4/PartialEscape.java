
public void method(boolean flag) {
    
    Class2 object2 = new Class2();
    
    tempValue = value;

    if(flag) {
        Class1 object1 = new Class1();
        object1.parameter = tempValue;
        return object1;
    }
    return object2;
}