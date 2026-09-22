public class TplClass1966 {

    private static final void method(int i) throws Throwable {
        String s = "test";
        int len = s.length();
        s = new StringBuilder(String.valueOf(s)).append(s).toString();
        len = len + len;
        s = new StringBuilder(String.valueOf(s)).append(s).toString();
        len = len + len;
        s = new StringBuilder(String.valueOf(s)).append(s).toString();
        len = len + len;
        if (s.length() != len) {
        }
    }
}

