import java.nio.charset.Charset;
import java.io.UnsupportedEncodingException;

public class TplClass2593 {

    private static final void method() throws Throwable {
        byte[] byteArray = "byteArray".getBytes();
        char[] charArray = new char[] { 'c', 'h', 'a', 'r', 'A', 'r', 'r', 'a', 'y' };
        String charsetName = "US-ASCII";
        Charset charset = Charset.forName("UTF-8");
        String string = "string";
        StringBuffer stringBuffer = new StringBuffer("stringBuffer");
        int[] codePoints = new int[] { 65, 66, 67, 68, 69 };
        StringBuilder stringBuilder = new StringBuilder("stringBuilder");
        String s1 = new String();
        String s2 = new String(byteArray);
        String s3 = new String(byteArray, 1);
        String s4 = new String(byteArray, 0, 4);
        String s5 = new String(byteArray, 2, 4, 5);
        try {
            String s6 = new String(byteArray, 2, 4, charsetName);
            String s7 = new String(byteArray, charsetName);
        } catch (UnsupportedEncodingException e) {
        }
        String s8 = new String(byteArray, 3, 3, charset);
        String s9 = new String(byteArray, charset);
        String s10 = new String(charArray);
        String s11 = new String(charArray, 0, 4);
        String s12 = new String(string);
        String s13 = new String(stringBuffer);
        String s14 = new String(codePoints, 1, 3);
        String s15 = new String(stringBuilder);
    }
}

