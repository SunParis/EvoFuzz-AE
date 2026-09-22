import java.lang.ref.WeakReference;

public class TplClass4633 {

    private static final void method(java.lang.Object keepAlive, boolean done, java.lang.ref.WeakReference[] weakReferences) throws Throwable {
        while (!done) {
            for (int j = 0; j < weakReferences.length; ++j) {
                keepAlive = weakReferences[j].get();
            }
            for (int j = 0; j < weakReferences.length; ++j) {
                weakReferences[j].clear();
            }
        }
    }
}

