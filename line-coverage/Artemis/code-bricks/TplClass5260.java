import java.util.concurrent.Future;

public class TplClass5260 {

    private static final void method(int NTASKS, java.util.concurrent.Future<java.lang.Void>[] task) throws Throwable {
        // check the result
        for (int i = 0; i < NTASKS; i++) {
            task[i].get();
        }
    }
}

