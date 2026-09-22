public class TplClass6015 {

    private static final void method(long initMemory) throws Throwable {
        for (int i = 1; i < 10; i++) {
            Thread.sleep(100);
            if (Runtime.getRuntime().totalMemory() > initMemory + 1000000)
                ;
        }
    }
}

