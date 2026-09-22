import java.io.File;

public class TplClass6004 {

    private static final void method(java.lang.Runtime r, java.lang.String[] envpWithNull, java.io.File dir) throws Throwable {
        try {
            r.exec(new String[] { "echo" }, envpWithNull, dir);
        }// OK
         catch (NullPointerException e) {
        }
    }
}

