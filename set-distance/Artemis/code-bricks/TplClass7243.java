import java.util.Arrays;

public class TplClass7243 {

    private static final void method(int failed) throws Throwable {
        Class<?>[] is = new Integer[0].getClass().getInterfaces();
        boolean thisFailed = false;
        if (is.length != 2)
            thisFailed = true;
        if (!is[0].getCanonicalName().equals("java.lang.Cloneable"))
            thisFailed = true;
        if (!is[1].getCanonicalName().equals("java.io.Serializable"))
            thisFailed = true;
        if (thisFailed) {
            failed++;
        }
    }
}

