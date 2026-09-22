public class TplClass5454 {

    private static final void method(java.lang.String[] args) throws Throwable {
        // linger?
        if (args.length > 0) {
            int delay = Integer.parseInt(args[0]);
            try {
                Thread.currentThread().sleep(delay);
            } catch (InterruptedException x) {
            }
        }
    }
}

