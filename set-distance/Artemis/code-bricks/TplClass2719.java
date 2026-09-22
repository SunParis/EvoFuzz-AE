public class TplClass2719 {

    private static final void method(char result, java.lang.String s, int pos) throws Throwable {
        try {
            result = s.charAt(pos);
        } catch (StringIndexOutOfBoundsException ignored) {
            result = '\0';
        }
    }
}

