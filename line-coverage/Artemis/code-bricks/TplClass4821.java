import java.util.Random;

public class TplClass4821 {

    private static final void method(java.util.Random generator) throws Throwable {
        // byte wrapper
        for (int x = 0; x < 100; x++) {
            byte y = (byte) generator.nextInt();
            Byte Y = new Byte(y);
            if (!Y.toString().equals(Byte.toString(y)))
                ;
        }
    }
}

