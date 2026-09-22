import java.util.Random;

public class TplClass4838 {

    private static final void method(java.util.Random generator) throws Throwable {
        short s = (short) generator.nextInt();
        Short S = new Short(s);
        if (!S.toString().equals(Short.toString(s)))
            ;
    }
}

