import java.lang.ref.WeakReference;

public class TplClass2558 {

    private static final void method(int i, int numValid, java.lang.ref.WeakReference[] wrefs) throws Throwable {
        if (wrefs[i].get() != null)
            numValid++;
    }
}

