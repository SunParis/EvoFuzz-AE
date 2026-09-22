public class TplClass5117 {

    private static final void method(byte[] bb, byte[] bytes) throws Throwable {
        if (bytes.length != bb.length) {
        } else {
            boolean diff = false;
            // Expect different byte[] between UTF-16LE and UTF-16BE
            // even though encoder was previously cached by last call
            // to getBytes().
            for (int i = 0; i < bytes.length; i++) {
                if (bytes[i] != bb[i])
                    diff = true;
            }
            if (!diff)
                ;
        }
    }
}

