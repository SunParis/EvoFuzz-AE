public class TplClass5499 {

    private static final void method(java.lang.StringBuffer sb) throws Throwable {
        try {
            char[] str = { 'a', 'b', 'c', 'd' };
            // test if negative length
            sb.append(str, 0, Integer.MIN_VALUE + 10);
        } catch (IndexOutOfBoundsException ex) {
        }
    }
}

