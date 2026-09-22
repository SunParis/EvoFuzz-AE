public class TplClass4467 {

    private static final void method(int count, java.lang.Object[] holder) throws Throwable {
        try {
            while (true) {
                // A bit over one page.
                holder[count++] = new Object[1025];
            }
        } catch (OutOfMemoryError e) {
        }
    }
}

