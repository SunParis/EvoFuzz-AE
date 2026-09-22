public class TplClass5792 {

    private static final void method(java.lang.StringBuffer s) throws Throwable {
        try {
            String nullstr = null;
            s.insert(3, nullstr);
            /* this will throw null pointer exception
                                  before the bug was fixed. */
            if (!s.toString().equals("FOOnullBAR")) {
            }
        } catch (NullPointerException npe) {
        }
    }
}

