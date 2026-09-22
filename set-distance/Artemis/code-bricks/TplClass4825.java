import java.util.Random;

public class TplClass4825 {

    private static final void method(java.util.Random generator) throws Throwable {
        // float wrapper
        for (int x = 0; x < 100; x++) {
            float f = generator.nextFloat();
            Float F = new Float(f);
            if (!F.toString().equals(Float.toString(f)))
                ;
        }
    }
}

