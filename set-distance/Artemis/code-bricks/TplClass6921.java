public class TplClass6921 {

    private static final void method(int i, int threadCount, int j, boolean fair) throws Throwable {
        // Non-fair queues are lifo in our implementation
        if (fair ? j != i : j != threadCount - 1 - i)
            ;
    }
}

