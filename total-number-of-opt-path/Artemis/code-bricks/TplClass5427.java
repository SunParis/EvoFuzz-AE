import java.util.TimerTask;
import java.util.Random;

public class TplClass5427 {

    private static final void method(int cancelled, long i, java.util.Random rnd, java.util.TimerTask task) throws Throwable {
        if (i != 1 && rnd.nextBoolean()) {
            task.cancel();
            cancelled++;
        }
    }
}

