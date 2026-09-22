import java.io.File;

public class TplClass6005 {

    private static final void method() throws Throwable {
        Runtime r = Runtime.getRuntime();
        java.io.File dir = new java.io.File(".");
        String[] envpWithNull = { "FOO=BAR", null };
        try {
            r.exec("echo", envpWithNull);
        }// OK
         catch (NullPointerException e) {
        }
        try {
            r.exec("echo", envpWithNull, dir);
        }// OK
         catch (NullPointerException e) {
        }
        try {
            r.exec(new String[] { "echo" }, envpWithNull);
        }// OK
         catch (NullPointerException e) {
        }
        try {
            r.exec(new String[] { "echo" }, envpWithNull, dir);
        }// OK
         catch (NullPointerException e) {
        }
    }
}

