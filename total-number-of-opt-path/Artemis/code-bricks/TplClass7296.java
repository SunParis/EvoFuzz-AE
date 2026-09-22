import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.io.OutputStreamWriter;

public class TplClass7296 {

    private static final void method(byte[] output, char[] input, int LEN) throws Throwable {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < LEN; i++) {
            int c = Character.MIN_SUPPLEMENTARY_CODE_POINT + 1;
            sb.append(Character.toChars(c));
        }
        input = sb.toString().toCharArray();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(bos, Charset.forName("UTF-8"));
        osw.write(input);
        osw.close();
        output = bos.toByteArray();
    }
}

