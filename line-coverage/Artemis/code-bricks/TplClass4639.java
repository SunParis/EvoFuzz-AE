import java.lang.ref.WeakReference;

public class TplClass4639 {

    private static final void method(java.lang.ref.WeakReference[] weakReferences) throws Throwable {
        Object o = new Object();
        for (int j = 0; j < weakReferences.length; ++j) {
            weakReferences[j] = new WeakReference(o);
        }
    }
}

