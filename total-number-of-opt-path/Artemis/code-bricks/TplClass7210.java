import java.net.IDN;

public class TplClass7210 {

    private static final void method() throws Throwable {
        String[] illegalNames = { "com..net", "com..", ".com", ".com." };
        String[] legalNames = { "example.com", "com\u3002", "com.", "." };
        for (String name : illegalNames) {
            try {
                IDN.toASCII(name, IDN.USE_STD3_ASCII_RULES);
            } catch (IllegalArgumentException iae) {
                // That's the right behavior.
            }
            try {
                IDN.toASCII(name);
            } catch (IllegalArgumentException iae) {
                // That's the right behavior.
            }
        }
        for (String name : legalNames) {
        }
    }
}

