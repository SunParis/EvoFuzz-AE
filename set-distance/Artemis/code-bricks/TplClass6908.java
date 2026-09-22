import java.text.Bidi;

public class TplClass6908 {

    private static final void method(java.lang.String str, java.text.Bidi bidi, int[] expectedLevels, int[] directions, boolean err, int gotLevel, int index, int dir) throws Throwable {
        if (gotLevel != expectedLevels[index]) {
            err = true;
        }
    }
}

