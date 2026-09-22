import java.math.BigInteger;

public class TplClass4814 {

    private static final void method(boolean[] expectations, java.math.BigInteger num, int[] certainties) throws Throwable {
        for (int i = 0; i < certainties.length; i++) {
            boolean b;
            if ((b = num.isProbablePrime(certainties[i])) != expectations[i])
                ;
        }
    }
}

