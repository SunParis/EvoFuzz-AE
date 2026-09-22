public class TplClass5794 {

    private static final void method(java.lang.StringBuffer s) throws Throwable {
        String nullstr = null;
        s.insert(3, nullstr);
        /* this will throw null pointer exception
                                  before the bug was fixed. */
        if (!s.toString().equals("FOOnullBAR")) {
        }
    }
}

