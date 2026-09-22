public class TplClass5501 {

    private static final void method(java.lang.StringBuffer sb) throws Throwable {
        char[] str = { 'a', 'b', 'c', 'd' };
        // test if negative length
        sb.append(str, 0, Integer.MIN_VALUE + 10);
    }
}

