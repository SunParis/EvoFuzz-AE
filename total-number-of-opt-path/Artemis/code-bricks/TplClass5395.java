import java.text.SimpleDateFormat;

public class TplClass5395 {

    private static final void method(java.text.SimpleDateFormat formatter, java.lang.String[] dstring, java.lang.String[] dformat, java.text.SimpleDateFormat resultFormatter, boolean[] dresult) throws Throwable {
        for (int i = 0; i < dstring.length; i++) {
            try {
                formatter = new SimpleDateFormat(dformat[i]);
                if (!dresult[i])
                    System.out.print("   <-- error!");
            } catch (Exception exception) {
            }
        }
    }
}

