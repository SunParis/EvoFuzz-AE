public class TplClass6001 {

    private static final void method(java.lang.Runtime r, java.lang.String[] envpWithNull) throws Throwable {
        try {
            r.exec("echo", envpWithNull);
        }// OK
         catch (NullPointerException e) {
        }
    }
}

