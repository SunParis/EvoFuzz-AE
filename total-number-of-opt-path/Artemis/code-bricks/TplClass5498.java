public class TplClass5498 {

    private static final void method(java.lang.StringBuilder sb) throws Throwable {
        try {
            char[] str = { 'a', 'b', 'c', 'd' };
            // test if negative length
            sb.append(str, 0, Integer.MIN_VALUE + 10);
        } catch (IndexOutOfBoundsException ex) {
        }
    }
}

