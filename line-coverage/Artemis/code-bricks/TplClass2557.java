import java.lang.ref.WeakReference;

public class TplClass2557 {

    private static final void method(java.lang.ref.WeakReference[] wrefs, int numValid) throws Throwable {
        for (int i = 0; i < wrefs.length; i++) {
            if (wrefs[i].get() != null)
                numValid++;
        }
    }
}

