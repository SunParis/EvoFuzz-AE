import java.text.SimpleDateFormat;

public class TplClass5398 {

    private static final void method(java.text.SimpleDateFormat formatter, java.lang.String[] dstring, int i, java.lang.String[] dformat, java.text.SimpleDateFormat resultFormatter, boolean[] dresult) throws Throwable {
        try {
            formatter = new SimpleDateFormat(dformat[i]);
            if (!dresult[i])
                System.out.print("   <-- error!");
        } catch (Exception exception) {
        }
    }
}

