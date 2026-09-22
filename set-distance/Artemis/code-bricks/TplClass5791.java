public class TplClass5791 {

    private static final void method(java.lang.StringBuffer s) throws Throwable {
        /* this will throw null pointer exception
                                  before the bug was fixed. */
        if (!s.toString().equals("FOOnullBAR")) {
        }
    }
}

