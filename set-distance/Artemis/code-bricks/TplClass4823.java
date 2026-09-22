import java.util.Random;

public class TplClass4823 {

    private static final void method(java.util.Random generator) throws Throwable {
        // int wrapper
        for (int x = 0; x < 100; x++) {
            int i = generator.nextInt();
            Integer I = new Integer(i);
            if (!I.toString().equals(Integer.toString(i)))
                ;
        }
    }
}

