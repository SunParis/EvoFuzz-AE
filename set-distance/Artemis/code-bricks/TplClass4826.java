import java.util.Random;

public class TplClass4826 {

    private static final void method(java.util.Random generator) throws Throwable {
        // double wrapper
        for (int x = 0; x < 100; x++) {
            double d = generator.nextDouble();
            Double D = new Double(d);
            if (!D.toString().equals(Double.toString(d)))
                ;
        }
    }
}

