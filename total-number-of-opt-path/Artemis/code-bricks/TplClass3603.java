import java.util.concurrent.atomic.AtomicInteger;

public class TplClass3603 {

    private static final void method(int winners, int i, int votes, java.util.concurrent.atomic.AtomicInteger[] called) throws Throwable {
        if (called[i].get() != 0) {
            winners++;
            votes += called[i].get();
        }
    }
}

