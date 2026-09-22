import java.io.OutputStreamWriter;

public class TplClass7291 {

    private static final void method(char[] input, java.io.OutputStreamWriter osw) throws Throwable {
        for (int i = 0; i < input.length; i += 7) osw.write(input, i, Math.min(input.length - i, 7));
    }
}

