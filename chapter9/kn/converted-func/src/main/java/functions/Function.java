package functions;

import io.quarkus.funqy.Funq;

public class Function {

    @Funq
    public Pounds convertFunc(Kg input) {
        return new Pounds(input.getKG());
    }

}
