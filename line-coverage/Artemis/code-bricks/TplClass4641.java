import java.lang.ref.WeakReference;

public class TplClass4641 {

    private static final void method(java.lang.Object keepAlive, java.lang.ref.WeakReference[] weakReferences) throws Throwable {
        for (int j = 0; j < weakReferences.length; ++j) {
            keepAlive = weakReferences[j].get();
        }
        for (int j = 0; j < weakReferences.length; ++j) {
            weakReferences[j].clear();
        }
    }
}

