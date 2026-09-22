import java.lang.ref.WeakReference;

public class TplClass4638 {

    private static final void method(boolean done, java.lang.ref.WeakReference[] weakReferences) throws Throwable {
        for (int i = 0; i < 1000; ++i) {
            Object o = new Object();
            for (int j = 0; j < weakReferences.length; ++j) {
                weakReferences[j] = new WeakReference(o);
            }
        }
        done = true;
    }
}

