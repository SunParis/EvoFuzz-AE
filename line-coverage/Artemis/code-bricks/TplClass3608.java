import java.util.concurrent.atomic.AtomicInteger;

public class TplClass3608 {

    private static final void method(int winners, int i, int votes, java.util.concurrent.atomic.AtomicInteger[] called) throws Throwable {
        winners++;
        votes += called[i].get();
    }
}

