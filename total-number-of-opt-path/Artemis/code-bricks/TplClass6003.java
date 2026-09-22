public class TplClass6003 {

    private static final void method(java.lang.Runtime r, java.lang.String[] envpWithNull) throws Throwable {
        try {
            r.exec(new String[] { "echo" }, envpWithNull);
        }// OK
         catch (NullPointerException e) {
        }
    }
}

