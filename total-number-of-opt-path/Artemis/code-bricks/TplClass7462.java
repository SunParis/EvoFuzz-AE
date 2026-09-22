public class TplClass7462 {

    private static final void method(boolean isNegative, java.lang.String s) throws Throwable {
        if (s.charAt(0) == '+') {
            s = s.substring(1);
        } else if (s.charAt(0) == '-') {
            s = s.substring(1);
            isNegative = true;
        }
    }
}

