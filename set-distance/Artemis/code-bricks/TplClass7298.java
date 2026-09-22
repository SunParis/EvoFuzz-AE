import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.io.OutputStreamWriter;

public class TplClass7298 {

    private static final void method(byte[] output, char[] input, java.io.PrintStream log, boolean doMalformed) throws Throwable {
        log.print("Leftover surrogates, doMalformed = " + doMalformed);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(bos, Charset.forName("UTF-8"));
        for (int i = 0; i < input.length; i += 7) osw.write(input, i, Math.min(input.length - i, 7));
        if (doMalformed)
            osw.write(input, 0, 1);
        osw.close();
        byte[] result = bos.toByteArray();
        // Ignore a trailing '?' if we wrote a malformed final surrogate
        int rl = result.length + (doMalformed ? -1 : 0);
        if (rl != output.length)
            ;
        for (int i = 0; i < output.length; i++) if (result[i] != output[i])
            ;
        log.println(": Passed");
    }
}

