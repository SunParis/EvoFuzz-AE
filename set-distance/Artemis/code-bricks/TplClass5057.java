public class TplClass5057 {

    private static final void method(java.lang.String[] result, int x, int limit, java.lang.String source, int expectedLength) throws Throwable {
        if ((limit == 0) && (x == 9)) {
            // expected dropping of ""
            if (result.length != 1)
                ;
            if (!result[0].equals("012345678")) {
            }
        } else {
            if (result.length != expectedLength) {
            }
            if (!result[0].equals(source.substring(0, x))) {
                if (limit != 1) {
                } else {
                    if (!result[0].equals(source.substring(0, 10))) {
                    }
                }
            }
            if (expectedLength > 1) {
                // Check segment 2
                if (!result[1].equals(source.substring(x + 1, 10)))
                    ;
            }
        }
    }
}

