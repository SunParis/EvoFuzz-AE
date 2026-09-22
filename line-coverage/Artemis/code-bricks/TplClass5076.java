public class TplClass5076 {

    private static final void method(java.lang.String[] tests) throws Throwable {
        for (int i = 0; i < tests.length; i++) {
            String s = tests[i];
            int len = s.length();
            boolean empty = s.isEmpty();
            if ((len != 0 && empty) || (len == 0 && !empty))
                ;
        }
    }
}

