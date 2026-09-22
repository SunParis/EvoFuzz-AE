public class TplClass581 {

    private static final void method(short[] a, java.lang.Short result2, java.lang.Short result1) throws Throwable {
        for (Short i : a) {
            result1 = (short) (result1 + i);
            result2 = (short) (result2 + new Short((short) (i + 1)));
        }
    }
}

