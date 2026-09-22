import java.util.List;

public class TplClass6037 {

    private static final void method(java.util.List<java.lang.Boolean> list, int numFalse) throws Throwable {
        for (int i = numFalse; i < 1000; i++) if (// Autounboxing doesn't work yet!
        !list.get(i).booleanValue())
            ;
    }
}

