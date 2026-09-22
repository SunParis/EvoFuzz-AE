public class TplClass6629 {

    private static final void method(int ncores, java.lang.Runnable busy) throws Throwable {
        for (int i = 0; i < ncores - 1; i++) new Thread(busy).start();
    }
}

