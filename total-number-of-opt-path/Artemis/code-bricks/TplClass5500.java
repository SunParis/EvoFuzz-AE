public class TplClass5500 {

    private static final void method(java.lang.StringBuilder sb) throws Throwable {
        char[] str = { 'a', 'b', 'c', 'd' };
        // test if negative length
        sb.append(str, 0, Integer.MIN_VALUE + 10);
    }
}

