import java.net.IDN;

public class TplClass7208 {

    private static final void method(java.lang.String name) throws Throwable {
        try {
            IDN.toASCII(name, IDN.USE_STD3_ASCII_RULES);
        } catch (IllegalArgumentException iae) {
            // That's the right behavior.
        }
    }
}

