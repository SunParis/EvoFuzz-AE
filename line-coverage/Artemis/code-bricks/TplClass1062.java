public class TplClass1062 {

    private static final void method(int i, int total, java.lang.Object[] o) throws Throwable {
        int limit = o.length;
        if (i < 20000)
            limit = 1;
        for (int j = 0; j < limit; j++) {
            total += o[j].hashCode();
        }
    }
}

