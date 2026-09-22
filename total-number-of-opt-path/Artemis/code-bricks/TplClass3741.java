public class TplClass3741 {

    private static final void method() throws Throwable {
        String[] strArray = new String[1];
        Object[] objArray = strArray;
        try {
            objArray[0] = new Integer(1);
        } catch (ArrayStoreException ase) {
        }
    }
}

