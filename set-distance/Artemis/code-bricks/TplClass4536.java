public class TplClass4536 {

    private static final void method(java.lang.Object obj) throws Throwable {
        for (; ; ) {
            synchronized (obj) {
                try {
                    obj.wait(1);
                } catch (Exception exc) {
                }
            }
        }
    }
}

