import java.lang.ref.WeakReference;

public class TplClass4628 {

    private static final void method(java.lang.Object temp, java.lang.ref.WeakReference[] weakReferences) throws Throwable {
        for (int j = 0; j < weakReferences.length; ++j) {
            weakReferences[j] = new WeakReference(temp);
        }
    }
}

