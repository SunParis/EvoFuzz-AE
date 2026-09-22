import java.util.ArrayList;

public class TplClass2868 {

    private static final void method(java.util.ArrayList<java.lang.Thread> threads) throws Throwable {
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
            }
        }
    }
}

