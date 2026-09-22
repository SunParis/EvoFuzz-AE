public class TplClass3808 {

    private static final void method(java.lang.String className) throws Throwable {
        // Other classes may also get loaded, ignore those.
        if (className.equals("LoadedByMyClassLoader") || className.equals("FirstSeenByMyClassLoader")) {
        }
    }
}

