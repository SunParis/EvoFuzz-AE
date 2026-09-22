public class TplClass5104 {

    private static final void method(byte[] bb, boolean diff, byte[] bytes) throws Throwable {
        // to getBytes().
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] != bb[i])
                diff = true;
        }
    }
}

