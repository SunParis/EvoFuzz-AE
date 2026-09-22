public class TplClass3637 {

    private static final void method(java.lang.Object[] global, java.lang.Object[] local) throws Throwable {
        // Very stupid linking.
        local[0] = global;
        for (int j = 1; j < local.length; j++) {
            local[j] = global[j];
        }
    }
}

