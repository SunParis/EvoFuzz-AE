import java.util.Random;

public class TplClass4824 {

    private static final void method(java.util.Random generator) throws Throwable {
        // long wrapper
        for (int x = 0; x < 100; x++) {
            long l = generator.nextLong();
            Long L = new Long(l);
            if (!L.toString().equals(Long.toString(l)))
                ;
        }
    }
}

