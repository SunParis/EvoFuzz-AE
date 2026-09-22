public class TplClass6012 {

    private static final void method() throws Throwable {
        try {
            Runtime.getRuntime().exec("");
        }// OK
         catch (IllegalArgumentException e) {
        }
        try {
            Runtime.getRuntime().exec(new String());
        }// OK
         catch (IllegalArgumentException e) {
        }
    }
}

