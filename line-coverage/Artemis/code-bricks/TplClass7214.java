import java.net.IDN;

public class TplClass7214 {

    private static final void method(java.lang.String[] illegalNames) throws Throwable {
        for (String name : illegalNames) {
            try {
                IDN.toASCII(name, IDN.USE_STD3_ASCII_RULES);
            } catch (IllegalArgumentException iae) {
                // That's the right behavior.
            }
        }
    }
}

