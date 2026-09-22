import java.net.IDN;

public class TplClass7209 {

    private static final void method(java.lang.String name) throws Throwable {
        try {
            IDN.toASCII(name);
        } catch (IllegalArgumentException iae) {
            // That's the right behavior.
        }
    }
}

