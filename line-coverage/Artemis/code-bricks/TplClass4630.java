import java.lang.ref.WeakReference;

public class TplClass4630 {

    private static final void method(java.lang.ref.WeakReference[] weakReferences, java.lang.Object o) throws Throwable {
        for (int j = 0; j < weakReferences.length; ++j) {
            weakReferences[j] = new WeakReference(o);
        }
    }
}

