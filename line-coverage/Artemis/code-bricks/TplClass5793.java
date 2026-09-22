public class TplClass5793 {

    private static final void method() throws Throwable {
        StringBuffer s = new StringBuffer("FOOBAR");
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

