public class TplClass4214 {

    private static final void method() throws Throwable {
        float f;
        double d;
        int i;
        long l;
        /* float --> int */
        f = 1234.5678f;
        i = (int) f;
        f = -1234.5678f;
        i = (int) f;
        /* double --> int */
        d = 1234.5678;
        i = (int) d;
        d = -1234.5678;
        i = (int) d;
        /* double --> long */
        d = 5678956789.0123;
        l = (long) d;
        d = -5678956789.0123;
        l = (long) d;
        /* int --> long */
        i = 7654;
        l = (long) i;
        i = -7654;
        l = (long) i;
        /* long --> int (with truncation) */
        l = 5678956789L;
        i = (int) l;
        l = -5678956789L;
        i = (int) l;
        /* int --> float */
        i = 1234;
        f = (float) i;
        i = -1234;
        f = (float) i;
    }
}

