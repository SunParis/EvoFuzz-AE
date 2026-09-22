public class TplClass1970 {

    private static final void method(int i) throws Throwable {
        String s = "test";
        int len = s.length();
        s = s + s;
        len = len + len;
        s = s + s;
        len = len + len;
        s = s + s;
        len = len + len;
        if (s.length() != len) {
        }
    }
}

