import java.net.IDN;

public class TplClass7215 {

    private static final void method() throws Throwable {
        // sequence.
        String[] illegalNames = { "www.example.com-", "-www.example.com", "-www.example.com-", "www.ex\u002Cmple.com", "www.ex\u007Bmple.com", "www.ex\u007Fmple.com" };
        String[] legalNames = { // www.ex-mple.com
        // www.exzmple.com
        "www.ex-ample.com", // www.xn--exmple-j43e.com
        "www.ex\u002Dmple.com", // www.xn--l8jeg.com
        "www.ex\u007Ample.com", // www.xn--fsq092h.com
        "www.ex\u3042mple.com", "www.\u3042\u3044\u3046.com", "www.\u793A\u4F8B.com" };
        for (String name : illegalNames) {
            try {
                IDN.toASCII(name, IDN.USE_STD3_ASCII_RULES);
            } catch (IllegalArgumentException iae) {
                // That's the right behavior.
            }
        }
        for (String name : legalNames) {
        }
    }
}

