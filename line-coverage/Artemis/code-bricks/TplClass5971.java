public class TplClass5971 {

    private static final void method(int i, java.lang.Process[] backgroundSleepers) throws Throwable {
        // should get immediate EOF, but might hang
        if (backgroundSleepers[i].getInputStream().read() != -1)
            ;
    }
}

