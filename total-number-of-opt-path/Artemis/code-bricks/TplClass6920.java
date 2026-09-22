public class TplClass6920 {

    private static final void method(java.lang.Thread t) throws Throwable {
        // Probably unnecessary, but should be bullet-proof
        while (t.getState() == Thread.State.RUNNABLE) Thread.yield();
    }
}

