import java.text.SimpleDateFormat;

public class TplClass5397 {

    private static final void method() throws Throwable {
        // 
        String[] dstring = { "97", "1997", "97", "1997", "01", "2001", "01", "2001", "1", "1", "11", "11", "111", "111" };
        String[] dformat = { "yy", "yy", "yyyy", "yyyy", "yy", "yy", "yyyy", "yyyy", "yy", "yyyy", "yy", "yyyy", "yy", "yyyy" };
        boolean[] dresult = { true, false, false, true, true, false, false, true, false, false, true, false, false, false };
        SimpleDateFormat formatter;
        SimpleDateFormat resultFormatter = new SimpleDateFormat("yyyy");
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

