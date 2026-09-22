import java.util.Random;

public class TplClass4822 {

    private static final void method(java.util.Random generator) throws Throwable {
        // short wrapper
        for (int x = 0; x < 100; x++) {
            short s = (short) generator.nextInt();
            Short S = new Short(s);
            if (!S.toString().equals(Short.toString(s)))
                ;
        }
    }
}

