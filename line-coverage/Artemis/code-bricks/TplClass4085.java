public class TplClass4085 {

    private static final void method(long time, java.lang.Object o) throws Throwable {
        while (System.currentTimeMillis() - time < 30000) {
            for (int j = 0; j < 10000; j++) {
                o = new Object[1000];
            }
            Runtime.getRuntime().gc();
            Thread.yield();
        }
    }
}

