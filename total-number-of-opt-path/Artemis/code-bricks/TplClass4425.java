public class TplClass4425 {

    private static final void method(java.lang.Object a, java.lang.Object b) throws Throwable {
        ((int[]) b)[20] = 99;
        for (int i = 0; i < ((int[]) a).length; i++) {
            ((int[]) a)[i] = ((int[]) b)[20] - 7;
            i++;
            ((int[]) a)[i] = ((int[]) b)[20] - 7;
        }
    }
}

